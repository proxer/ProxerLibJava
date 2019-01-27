package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum holding the available notification filters.
 *
 * @author Ruben Gees
 */
enum class NotificationFilter {

    @Json(name = "0")
    ALL,

    @Json(name = "1")
    UNREAD,

    @Json(name = "2")
    READ
}
