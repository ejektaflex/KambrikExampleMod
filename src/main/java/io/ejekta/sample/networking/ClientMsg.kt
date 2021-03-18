package io.ejekta.sample.networking

import io.ejekta.kambrik.ext.wrapToPacketByteBuf
import io.ejekta.sample.KambrikMessage
import io.ejekta.sample.packet.IPacketInfo
import io.ejekta.sample.packet.IPacketInfo.Companion.dummy
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.fabric.impl.networking.server.ServerPlayNetworkAddon
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity

@Serializable
open class ClientMsg<M : ClientMsg<M>>(@Transient val info: IPacketInfo<M> = dummy()) : KambrikMessage, IPacketInfo<M> by info {

    data class ClientMsgContext(
        val client: MinecraftClient,
        val handler: ClientPlayNetworkHandler,
        val buf: PacketByteBuf,
        val responseSender: PacketSender
    )

    open fun onClientReceived(ctx: ClientMsgContext) {

    }

    fun sendToClient(player: ServerPlayerEntity) {
        ServerPlayNetworking.send(
            player,
            id,
            serializePacket(this as M).wrapToPacketByteBuf()
        )
    }
}