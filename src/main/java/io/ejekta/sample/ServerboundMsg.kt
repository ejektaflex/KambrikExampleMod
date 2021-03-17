package io.ejekta.sample

import io.ejekta.kambrik.ext.wrapToPacketByteBuf
import io.ejekta.sample.packet.IPacketInfo
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity


interface ServerboundMsg<M : ServerboundMsg<M>> : KambrikMessage {

    data class Context(
        val server: MinecraftServer,
        val player: ServerPlayerEntity,
        val handler: ServerPlayNetworkHandler,
        val responseSender: PacketSender
    )

    fun onReceived(ctx: Context) {
        println("ON GOT!!! :D!! ")
    }

    fun sendToServer(handler: ServerMsgHandler<M>) {
        handler.sendToServer(this as M)
    }

}