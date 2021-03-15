package io.ejekta.sample.packet

import io.ejekta.kambrikx.api.serial.nbt.NbtFormat
import kotlinx.serialization.KSerializer
import net.minecraft.util.Identifier

interface IPacketInfo<S> {
    val id: Identifier
    val format: NbtFormat
        get() = NbtFormat.Default
    val serializer: KSerializer<S>
}