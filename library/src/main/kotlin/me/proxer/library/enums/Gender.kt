package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available genders.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "UNKNOWN")
enum class Gender {

    @Json(name = "m")
    MALE,

    @Json(name = "f")
    FEMALE,

    @Json(name = "o")
    OTHER,

    @Json(name = "")
    UNKNOWN
}
