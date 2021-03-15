package io.ejekta.sample

import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier



/* Syntax A
To use:
    StringMsg.registerOnServer()
    StringMsg.sendToServer(StringMsg.Payload("Hello!"))
 */
object StringMsg : ServerMsgHandler<StringMsg.Payload>(Payload.serializer()) {
    override val id: Identifier = Identifier(SampleMod.ID, "test_msg")

    @Serializable
    class Payload(val msg: String) : KambrikServerboundMessage()
    }

/* Syntax B
To use:
    StringMsg.Handler.registerOnServer()
    StringMsg.Handler.sendToServer(StringMessage("Hello!"))
 */
@Serializable
class StringMessage(val msg: String) : KambrikServerboundMessage() {
    object Handler : ServerMsgHandler<StringMessage>(serializer()) {
        override val id: Identifier = Identifier(SampleMod.ID, "test_msg")
    }
}