@file:Suppress("NOTHING_TO_INLINE")

package me.proxer.library.util

import com.squareup.moshi.Json

/**
 * Utility object for common operations.
 *
 * @author Ruben Gees
 */
object ProxerUtils {

    /**
     * Returns the name of the passed enum instance (Using that specified in the `@Json` annotation).
     */
    inline fun getApiEnumName(it: Enum<*>) = it.javaClass.getDeclaredField(it.name)
        ?.getAnnotation(Json::class.java)
        ?.name

    /**
     * Returns the name of the passed enum instance (Using that specified in the `@Json` annotation).
     */
    inline fun getSafeApiEnumName(it: Enum<*>) = getApiEnumName(it) ?: throw IllegalStateException(
        "Field ${it.name} not found in Enum ${it.javaClass.declaringClass.name}"
    )

    /**
     * Converts the passed [value] string to an instance of the type [T].
     *
     * If the conversion is not possible, because the passed string was invalid, null is returned.
     * If an invalid enum type is passed (one that is not in this library), an exception is thrown.
     */
    inline fun <T : Enum<T>> toApiEnum(type: Class<T>, value: String) = type.declaredFields.asSequence()
        .find { it.getAnnotation(Json::class.java)?.name?.equals(value, ignoreCase = true) == true }
        ?.let { java.lang.Enum.valueOf(type, it.name) }

    /**
     * Converts the passed [value] string to an instance of the type [T].
     *
     * If the conversion is not possible, because the passed string was invalid, null is returned.
     * If an invalid enum type is passed (one that is not in this library), an exception is thrown.
     */
    inline fun <T : Enum<T>> toSafeApiEnum(type: Class<T>, value: String) = toApiEnum(type, value)
        ?: throw IllegalStateException("Value " + value + " not found for Enum " + type.name)

    /**
     * Converts the passed [value] string to an instance of the type [T].
     *
     * If the conversion is not possible, because the passed string was invalid, null is returned.
     * If an invalid enum type is passed (one that is not in this library), an exception is thrown.
     */
    inline fun <reified T : Enum<T>> toApiEnum(value: String) = toApiEnum(T::class.java, value)

    /**
     * Converts the passed [value] string to an instance of the type [T].
     *
     * If the conversion is not possible, because the passed string was invalid, null is returned.
     * If an invalid enum type is passed (one that is not in this library), an exception is thrown.
     */
    inline fun <reified T : Enum<T>> toSafeApiEnum(value: String) = toSafeApiEnum(T::class.java, value)
}
