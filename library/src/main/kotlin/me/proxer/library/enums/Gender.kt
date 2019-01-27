package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the available genders.
 *
 * @author Ruben Gees
 */
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
