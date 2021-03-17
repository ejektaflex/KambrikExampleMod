package io.ejekta.sample

import com.mojang.brigadier.arguments.StringArgumentType.getString
import io.ejekta.kambrik.Kambrik
import io.ejekta.kambrikx.api.serial.nbt.NbtFormat
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.command.argument.EntityArgumentType.*
import net.minecraft.entity.LivingEntity
import net.minecraft.text.LiteralText

class SampleMod : ModInitializer {

    internal companion object {
        const val ID = "sample"
    }

    private val logger = Kambrik.Logging.createLogger(ID)

    override fun onInitialize() {
        logger.info("Kambrik Sample Mod Says Hello!")
        registerCommands()

        println("NBT DEFAULT: ${NbtFormat.Default}")

        StringMsg.registerOnServer()

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

                    StringMsg("Hai!").sendToServer()

                    1
                }

                literal("doooot") runs {
                    it.source.minecraftServer.execute {
                        StringMsg("Hai!").sendToServer()
                    }
                    1
                }


            }
        })


    }

}