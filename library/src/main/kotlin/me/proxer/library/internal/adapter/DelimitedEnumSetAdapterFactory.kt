package me.proxer.library.internal.adapter

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.proxer.library.util.ProxerUtils
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.EnumSet
import java.util.Locale

/**
 * @author Ruben Gees
 */
internal class DelimitedEnumSetAdapterFactory : JsonAdapter.Factory {

    @Suppress("ReturnCount")
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)

        if (rawType !== Set::class.java || type !is ParameterizedType) {
            return null
        }

        val parameterType = type.actualTypeArguments[0]

        if (parameterType !is Class<*> || !parameterType.isEnum) {
            return null
        }

        val annotation = annotations.filterIsInstance(DelimitedEnumSet::class.java).firstOrNull() ?: return null

        // There is no other way in Kotlin to instantiate a generic class with an enum type parameter, when not
        // statically knowing the exact enum.
        // Idea from here: https://stackoverflow.com/a/46422600/4279995
        @Suppress("UNCHECKED_CAST")
        return DelimitedEnumSetAdapter(
            parameterType as Class<PlaceHolderEnum>,
            annotation.delimiter
        )
    }

    private class DelimitedEnumSetAdapter<T : Enum<T>> internal constructor(
        private val enumType: Class<T>,
        private val delimiter: String
    ) : JsonAdapter<Set<T>>() {

        private val enumMap = enumType.enumConstants
            .map { constant ->
                try {
                    val annotation = enumType.getField(constant.name).getAnnotation(Json::class.java)
                    val name = annotation?.name ?: constant.name

                    name.toLowerCase(Locale.US) to constant
                } catch (error: NoSuchFieldException) {
                    throw AssertionError("Missing field in ${enumType.name}", error)
                }
            }
            .toMap()

        private val fallbackEnum = enumType.getAnnotation(FallbackEnum::class.java)?.let {
            java.lang.Enum.valueOf(enumType, it.name)
        }

        override fun fromJson(reader: JsonReader): Set<T> {
            return if (reader.peek() == JsonReader.Token.NULL) {
                reader.nextNull<Any>()

                emptySet()
            } else {
                val rawParts = reader.nextString().trim().toLowerCase(Locale.US)

                if (rawParts.isEmpty()) {
                    emptySet()
                } else {
                    convertToEnumConstants(reader, rawParts.split(delimiter))
                }
            }
        }

        override fun toJson(writer: JsonWriter, value: Set<T>?) {
            if (value == null) {
                writer.nullValue()
            } else {
                writer.value(value.joinToString(delimiter) { ProxerUtils.getSafeApiEnumName(it) })
            }
        }

        private fun convertToEnumConstants(reader: JsonReader, parts: List<String>): Set<T> {
            return parts
                .map {
                    enumMap[it] ?: fallbackEnum ?: throw JsonDataException(
                        "Expected one of ${enumMap.keys} but was $it at path ${reader.path}"
                    )
                }
                .let { EnumSet.copyOf(it) }
        }
    }

    @Suppress("UnusedPrivateClass")
    private enum class PlaceHolderEnum
}
