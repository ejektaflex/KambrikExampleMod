package io.ejekta.sample

import io.ejekta.sample.packet.IPacketInfo
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer
import net.minecraft.util.Identifier

/* Syntax B
To use:
    StringMsg.registerOnServer()
    StringMessage("Hello!").sendToServer()
 */
@Serializable
class StringMessage(val msg: String) : ServerboundMessage<StringMessage>, IPacketInfo<StringMessage> by Handler {

    override fun onReceived(context: ServerboundMessage.ServerMsgContext) {
        println("Received this message: $msg")
    }

    companion object Handler : ServerMsgHandler<StringMessage>(Identifier(SampleMod.ID, "test_msg"), serializer())
}