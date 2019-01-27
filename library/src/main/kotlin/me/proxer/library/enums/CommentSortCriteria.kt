package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum holding the available sort options for comments.
 *
 * @author Ruben Gees
 */
enum class CommentSortCriteria {

    @Json(name = "rating")
    RATING,

    @Json(name = "")
    TIME
}
