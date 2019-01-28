package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available synonym types.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "OTHER")
enum class SynonymType {

    @Json(name = "name")
    ORIGINAL,

    @Json(name = "nameeng")
    ENGLISH,

    @Json(name = "nameger")
    GERMAN,

    @Json(name = "namejap")
    JAPANESE,

    @Json(name = "namekor")
    KOREAN,

    @Json(name = "namezhn")
    CHINESE,

    @Json(name = "syn")
    ORIGINAL_ALTERNATIVE,

    @Json(name = "")
    OTHER
}
