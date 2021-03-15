package io.ejekta.sample

import io.ejekta.kambrik.ext.wrapToPacketByteBuf
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

@Serializable
abstract class KambrikServerboundMessage : KambrikMessage() {

    open fun onGet(server: MinecraftServer,
              player: ServerPlayerEntity,
              handler: ServerPlayNetworkHandler,
              responseSender: PacketSender
    ) {
        println("ON GOT!!! :D!! ")
    }

}