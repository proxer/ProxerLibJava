package me.proxer.library.internal.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

/**
 * @author Ruben Gees
 */
internal class BooleanAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return if (type === Boolean::class.javaPrimitiveType || type === Boolean::class.javaObjectType) {
            if (annotations.any { it is NumberBasedBoolean }) {
                NumberBasedBooleanAdapter().nullSafe()
            } else {
                moshi.nextAdapter<Any>(this, type, annotations)
            }
        } else {
            null
        }
    }

    private class NumberBasedBooleanAdapter : JsonAdapter<Boolean>() {

        override fun fromJson(reader: JsonReader): Boolean? {
            return when (val nextToken = reader.peek()) {
                JsonReader.Token.NUMBER -> reader.nextInt().toBoolean(reader)
                JsonReader.Token.STRING -> {
                    val jsonString = reader.nextString()
                    val jsonNumber = jsonString.toIntOrNull()

                    jsonNumber?.toBoolean(reader) ?: jsonString.toBoolean(reader)
                }
                else -> throw JsonDataException(
                    "Expected a number, string or null but was ${nextToken.name} at path ${reader.path}"
                )
            }
        }

        override fun toJson(writer: JsonWriter, value: Boolean?) {
            when (value) {
                null -> writer.nullValue()
                else -> writer.value(if (value) 1 else 0)
            }
        }

        private fun Int.toBoolean(reader: JsonReader) = when (this) {
            0 -> false
            1 -> true
            else -> throw JsonDataException("Expected a 1 or 0 but was $this at path ${reader.path}")
        }

        private fun String.toBoolean(reader: JsonReader) = when (this) {
            "true" -> true
            "false" -> false
            else -> throw JsonDataException("Expected a true or false but was $this at path ${reader.path}")
        }
    }
}
