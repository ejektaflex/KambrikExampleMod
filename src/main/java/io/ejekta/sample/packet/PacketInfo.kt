package io.ejekta.sample.packet

import io.ejekta.kambrikx.api.serial.nbt.NbtFormat
import kotlinx.serialization.KSerializer
import net.minecraft.util.Identifier

data class PacketInfo<S>(
    override val id: Identifier,
    override val serializer: KSerializer<S>,
    override val format: NbtFormat = NbtFormat.Default
) : IPacketInfo<S>
