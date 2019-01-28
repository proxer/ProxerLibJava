package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum for selecting the length bound.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class LengthBound {

    @Json(name = "up")
    HIGHER,

    @Json(name = "down")
    LOWER
}
