package io.ejekta.sample

import io.ejekta.sample.packet.IPacketInfo
import io.ejekta.sample.packet.serverMsgHandler
import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier

/* Syntax C
To use:
    StringMsg.Handler.registerOnServer()
    StringMessage("Hello!").sendToServer()
 */
@Serializable
class StringMessage(val msg: String) : ServerboundMsg<StringMessage>, IPacketInfo<StringMessage> by Handler {

    override fun onReceived(ctx: ServerboundMsg.Context) {
        println("Received message from ${ctx.player} saying: $msg")
    }

    companion object {
        val Handler = serverMsgHandler<StringMessage>(Identifier(SampleMod.ID, "test_msg"))
    }
}
