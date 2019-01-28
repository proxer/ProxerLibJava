package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available notification filters.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class NotificationFilter {

    @Json(name = "0")
    ALL,

    @Json(name = "1")
    UNREAD,

    @Json(name = "2")
    READ
}
