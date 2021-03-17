package io.ejekta.sample

import io.ejekta.sample.packet.IPacketInfo
import io.ejekta.sample.packet.PacketInfo
import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier

/* Syntax D
To use:
    StringMsg.registerOnServer()
    StringMsg("Hello!").sendToServer()
 */

@Serializable
class StringMsg(val msg: String) : ServerMsg<StringMsg>(this) {

    override fun onServerReceived(ctx: Context) {
        println("We got a message! It says!: $msg")
    }

    companion object Handler : ServerMsgHandler<StringMsg>(
        PacketInfo(Identifier(SampleMod.ID, "test_msg") to StringMsg.serializer())
    )

}
