package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the available synonym types.
 *
 * @author Ruben Gees
 */
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
