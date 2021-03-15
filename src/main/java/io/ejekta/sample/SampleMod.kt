package io.ejekta.sample

import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.StringArgumentType.getString
import io.ejekta.kambrik.Kambrik
import io.ejekta.kambrik.api.command.getString
import io.ejekta.kambrikx.api.serial.nbt.NbtFormat
import kotlinx.serialization.modules.SerializersModule
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.command.argument.EntityArgumentType.*
import net.minecraft.entity.LivingEntity
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier

class SampleMod : ModInitializer {

    internal companion object {
        const val ID = "sample"
    }

    private val logger = Kambrik.Logging.createLogger(ID)

    override fun onInitialize() {
        logger.info("Kambrik Sample Mod Says Hello!")
        registerCommands()

        StringMessage.Handler.registerOnServer()

    }

    private fun registerCommands() {

        logger.info("Setting up commands..")

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher, dedicated ->
            Kambrik.Command.addCommand("sample", dispatcher) {

                // Usage: /sample echo [word]
                // What it does: Echoes text back to the user.
                literal("echo") {
                    stringArg("word") runs {
                        val word = getString(it, "word")
                        it.source.sendFeedback(LiteralText("Word is: $word"), false)
                        1
                    }
                }

                // Usage: /sample heal [target]
                // What it does: Heals all living entities that match the target (@p, @s, "PlayerName", etc)
                literal("heal") {
                    argument(entities(), "entities") {
                        // Execution inside of argument instead
                        executes {
                            val entities = getEntities(it, "entities")
                            entities.forEach { e ->
                                if (e is LivingEntity) {
                                    e.heal(e.maxHealth - e.health)
                                }
                            }
                            1
                        }
                    }
                }

                literal("doot") runs {
                    StringMessage.Handler.sendToServer(StringMessage("Hello!"))
                    1
                }


            }
        })


    }

}