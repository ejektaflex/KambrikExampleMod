package io.ejekta.sample

import io.ejekta.kambrik.ext.unwrapToTag
import io.ejekta.kambrik.ext.wrapToPacketByteBuf
import io.ejekta.kambrikx.api.serial.nbt.NbtFormat
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Transient
import kotlinx.serialization.modules.SerializersModule
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

abstract class ServerMsgHandler<M : KambrikServerboundMessage>(val serializer: KSerializer<M>) : ServerPlayNetworking.PlayChannelHandler {

    abstract val id: Identifier

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
            data.onGet(server, player, handler, responseSender)
            println("KSM!!: :D!")
        }
    }

    fun sendToServer(payload: M) {
        ClientPlayNetworking.send(
            id,
            format.encodeToTag(serializer, payload).wrapToPacketByteBuf()
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Transient
    open val format: NbtFormat = NbtFormat {
        serializersModule = SerializersModule {
            include(NbtFormat.BuiltInSerializers)
        }
    }

}