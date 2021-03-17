package io.ejekta.sample

import io.ejekta.sample.packet.PacketInfo
import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier

/* Syntax C
To use:
    StringMsg.registerOnServer()
    StringMessage.sendToServer(StringMessage.Payload("Hello!"))
 */
object StrMsg : ServerMsgHandler<StrMsg.Payload>() {

    override val info = PacketInfo(
        Identifier(SampleMod.ID, "test_msg") to Payload.serializer()
    )

    operator fun invoke(msg: String) = Payload(msg)

    @Serializable
    class Payload(val msg: String) : ServerboundMsg<Payload> {
        override fun onReceived(ctx: ServerboundMsg.Context) {
            println("Received message from ${ctx.player} saying: $msg")
        }
    }

}
