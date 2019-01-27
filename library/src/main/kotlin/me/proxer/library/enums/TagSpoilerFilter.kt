package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum for filtering tags with spoilers.
 *
 * @author Ruben Gees
 */
enum class TagSpoilerFilter {

    @Json(name = "spoiler_0")
    NO_SPOILERS,

    @Json(name = "spoiler_10")
    ALL,

    @Json(name = "spoiler_1")
    ONLY_SPOILERS
}
