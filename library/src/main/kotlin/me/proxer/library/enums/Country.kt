package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available countries.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "OTHER")
enum class Country {

    @Json(name = "de")
    GERMANY,

    @Json(name = "en")
    ENGLAND,

    @Json(name = "us")
    UNITED_STATES,

    @Json(name = "jp")
    JAPAN,

    @Json(name = "kr")
    KOREA,

    @Json(name = "zh")
    CHINA,

    @Json(name = "int")
    INTERNATIONAL,

    @Json(name = "misc")
    OTHER,

    @Json(name = "")
    NONE
}
