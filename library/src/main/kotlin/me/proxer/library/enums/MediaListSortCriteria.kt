package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available sort criteria for the media list.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class MediaListSortCriteria {

    @Json(name = "title")
    TITLE,

    @Json(name = "clicks")
    CLICKS,

    @Json(name = "rating")
    RATING
}
