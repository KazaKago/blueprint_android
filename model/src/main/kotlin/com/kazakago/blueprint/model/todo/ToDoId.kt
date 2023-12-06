package com.kazakago.blueprint.model.todo

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@JvmInline
@Serializable(with = ToDoId.Serializer::class)
value class ToDoId(val value: Long) {

    class Serializer : KSerializer<ToDoId> {

        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ToDoId", PrimitiveKind.LONG)

        override fun serialize(encoder: Encoder, value: ToDoId) {
            encoder.encodeLong(value.value)
        }

        override fun deserialize(decoder: Decoder): ToDoId {
            return ToDoId(decoder.decodeLong())
        }
    }
}
