package io.ejekta.sample.packet

import io.ejekta.sample.ServerMsgHandler
import io.ejekta.sample.ServerboundMsg
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import net.minecraft.util.Identifier

inline fun <reified H : ServerboundMsg<H>> serverMsgHandler(id: Identifier, serial: KSerializer<H> = serializer()): ServerMsgHandler<H> {
    return object : ServerMsgHandler<H>, IPacketInfo<H> by PacketInfo(id, serial) {

    }
}