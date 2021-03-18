package io.ejekta.sample

import io.ejekta.sample.networking.ClientMsg
import io.ejekta.sample.networking.ClientMsgHandler
import io.ejekta.sample.networking.ServerMsg
import io.ejekta.sample.networking.ServerMsgHandler
import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier

/* Syntax D
To use:
    StringMsg.registerOnServer()
    StringMsg("Hello!").sendToServer()
 */

@Serializable
data class StringMsg(val msg: String) : ServerMsg<StringMsg>(Handler) {

    override fun onServerReceived(ctx: ServerMsgContext) {
        println("We got a message on the server side! It says!: $msg")
        ToPlayer("Server says hello!").sendToClient(ctx.player)
    }

    companion object {
        val Handler = ServerMsgHandler(Identifier(SampleMod.ID, "test_msg") to serializer())
    }

}

@Serializable
data class ToPlayer(val msg: String) : ClientMsg<ToPlayer>(Handler) {

    override fun onClientReceived(ctx: ClientMsgContext) {
        println("We got a message on the client side! It says!: $msg")
    }

    companion object {
        val Handler = ClientMsgHandler(Identifier(SampleMod.ID, "test_msg_2") to serializer())
    }

}
