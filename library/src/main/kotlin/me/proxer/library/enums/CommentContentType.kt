package me.proxer.library.enums

import com.squareup.moshi.Json

/**
 * Enum holding possiblke values for the "has"-filter of the [me.proxer.library.api.user.UserCommentsEndpoint].
 *
 * @author Ruben Gees
 */
enum class CommentContentType {

    @Json(name = "rating")
    RATING,

    @Json(name = "comment")
    COMMENT
}
