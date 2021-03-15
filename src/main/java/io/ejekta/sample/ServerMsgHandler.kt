package io.ejekta.sample

import io.ejekta.kambrik.ext.unwrapToTag
import io.ejekta.kambrik.ext.wrapToPacketByteBuf
import io.ejekta.sample.packet.IPacketInfo
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity

interface ServerMsgHandler<M : ServerboundMsg<M>> : ServerPlayNetworking.PlayChannelHandler,
    IPacketInfo<M> {

    //override val id: Identifier
    //override val serializer: KSerializer<KambrikServerboundMessage>

    fun registerOnServer() {
        ServerPlayNetworking.registerGlobalReceiver(id, ::receive)
    }

    override fun receive(
        server: MinecraftServer,
        player: ServerPlayerEntity,
        handler: ServerPlayNetworkHandler,
        buf: PacketByteBuf,
        responseSender: PacketSender
    ) {
        val contents = buf.unwrapToTag()
        val data = format.decodeFromTag(serializer, contents)
        server.execute {
            println("KSM??: :D!")
            data.onReceived(ServerboundMsg.Context(server, player, handler, responseSender))
            println("KSM!!: :D!")
        }
    }

    fun sendToServer(payload: M) {
        ClientPlayNetworking.send(
            id,
            format.encodeToTag(serializer, payload).wrapToPacketByteBuf()
        )
    }

}