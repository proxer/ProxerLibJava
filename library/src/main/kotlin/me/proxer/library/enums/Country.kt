package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the available countries.
 *
 * @author Ruben Gees
 */
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
