package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum for selecting the length bound.
 *
 * @author Ruben Gees
 */
enum class LengthBound {

    @Json(name = "up")
    HIGHER,

    @Json(name = "down")
    LOWER
}
