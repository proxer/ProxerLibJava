package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available conference types.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class ConferenceType {

    @Json(name = "favour")
    FAVORITE,

    @Json(name = "block")
    BLOCKED,

    @Json(name = "group")
    GROUP,

    @Json(name = "default")
    DEFAULT
}
