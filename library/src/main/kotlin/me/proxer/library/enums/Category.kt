package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available categories.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class Category {

    @Json(name = "anime")
    ANIME,

    @Json(name = "manga")
    MANGA,

    @Json(name = "novel")
    NOVEL
}
