package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available sort options for comments.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = false)
enum class CommentSortCriteria {

    @Json(name = "rating")
    RATING,

    @Json(name = "")
    TIME
}
