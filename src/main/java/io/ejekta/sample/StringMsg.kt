package io.ejekta.sample

import kotlinx.serialization.Serializable
import net.minecraft.util.Identifier


object StringMsg : ServerMsgHandler<StringMsg.Payload>(Payload.serializer()) {
    override val id: Identifier = Identifier(SampleMod.ID, "testing_one")

    @Serializable
    class Payload(val msg: String) : KambrikServerboundMessage() {
        override fun getId() = id
        override fun getFormat() = format
    }
}