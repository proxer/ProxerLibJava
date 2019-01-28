package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available seasons.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "UNSPECIFIED")
enum class Season {

    @Json(name = "1")
    WINTER,

    @Json(name = "2")
    SPRING,

    @Json(name = "3")
    SUMMER,

    @Json(name = "4")
    AUTUMN,

    @Json(name = "5")
    UNSPECIFIED
}
