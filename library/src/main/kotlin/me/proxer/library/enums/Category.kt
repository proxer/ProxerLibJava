package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum holding the available categories.
 *
 * @author Ruben Gees
 */
enum class Category {

    @Json(name = "anime")
    ANIME,

    @Json(name = "manga")
    MANGA
}
