package io.ejekta.sample

import io.ejekta.kambrik.ext.unwrapToTag
import io.ejekta.sample.packet.IPacketInfo
import io.ejekta.sample.packet.PacketInfo
import kotlinx.serialization.KSerializer
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

abstract class ServerMsgHandler<M : ServerMsg<M>>(val info: IPacketInfo<M>) : ServerPlayNetworking.PlayChannelHandler, IPacketInfo<M> by info {


    //override val id: Identifier
    //override val serializer: KSerializer<KambrikServerboundMessage>

    fun packet(idPair: Pair<Identifier, KSerializer<M>>) = PacketInfo(idPair.first, idPair.second)

    fun registerOnServer() {
        ServerPlayNetworking.registerGlobalReceiver(info.id, ::receive)
    }

    override fun receive(
        server: MinecraftServer,
        player: ServerPlayerEntity,
        handler: ServerPlayNetworkHandler,
        buf: PacketByteBuf,
        responseSender: PacketSender
    ) {
        val contents = buf.unwrapToTag()
        val data = info.deserializePacket(contents)
        server.execute {
            println("KSM??: :D!")
            data.onServerReceived(ServerMsg.Context(server, player, handler, responseSender))
            println("KSM!!: :D!")
        }
    }

}