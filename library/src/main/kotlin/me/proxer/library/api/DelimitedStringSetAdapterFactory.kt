package me.proxer.library.api

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author Ruben Gees
 */
internal class DelimitedStringSetAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)

        if (rawType !== Set::class.java || type !is ParameterizedType) {
            return null
        }

        val parameterType = type.actualTypeArguments[0]

        if (parameterType !== String::class.java) {
            return null
        }

        val annotation = annotations.filterIsInstance(DelimitedStringSet::class.java).firstOrNull() ?: return null

        return DelimitedStringSetAdapter(annotation.delimiter, annotation.valuesToKeep)
    }

    private class DelimitedStringSetAdapter internal constructor(
        private val delimiter: String,
        valuesToKeep: Array<String>
    ) : JsonAdapter<Set<String>>() {

        private val valuesToKeep = valuesToKeep.map { it.split(delimiter).toSet() }

        override fun fromJson(reader: JsonReader): Set<String> {
            return if (reader.peek() == JsonReader.Token.NULL) {
                reader.nextNull<Any>()

                emptySet()
            } else {
                val rawParts = reader.nextString().trim()

                if (rawParts.isEmpty()) {
                    emptySet()
                } else {
                    val splitParts = rawParts.split(delimiter).toMutableSet()

                    for (valueToKeep in valuesToKeep) {
                        if (splitParts.removeAll(valueToKeep)) {
                            splitParts.add(valueToKeep.joinToString(delimiter))
                        }
                    }

                    splitParts
                }
            }
        }

        override fun toJson(writer: JsonWriter, value: Set<String>?) {
            if (value == null) {
                writer.nullValue()
            } else {
                writer.value(value.joinToString(delimiter))
            }
        }
    }
}
