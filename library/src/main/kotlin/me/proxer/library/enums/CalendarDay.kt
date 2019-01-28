package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available days of the calendar.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class CalendarDay {

    @Json(name = "mon")
    MONDAY,

    @Json(name = "tue")
    TUESDAY,

    @Json(name = "wed")
    WEDNESDAY,

    @Json(name = "thu")
    THURSDAY,

    @Json(name = "fri")
    FRIDAY,

    @Json(name = "sat")
    SATURDAY,

    @Json(name = "sun")
    SUNDAY
}
