package io.ejekta.sample

import io.ejekta.kambrik.Kambrik
import io.ejekta.kambrik.KambrikMod
import io.ejekta.kambrikx.api.serial.nbt.NbtFormat
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.modules.SerializersModule
import net.minecraft.util.Identifier

@Serializable
abstract class KambrikMessage {

    abstract fun getId(): Identifier

    abstract fun getFormat(): NbtFormat

}

