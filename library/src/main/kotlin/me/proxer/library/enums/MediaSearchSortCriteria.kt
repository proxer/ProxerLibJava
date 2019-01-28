package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available sort types for the media list.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class MediaSearchSortCriteria {

    @Json(name = "relevance")
    RELEVANCE,

    @Json(name = "clicks")
    CLICKS,

    @Json(name = "rating")
    RATING,

    @Json(name = "count")
    EPISODE_AMOUNT,

    @Json(name = "name")
    NAME
}
