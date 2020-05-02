package me.proxer.library.entity.messenger

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Entity containing the results of a link check.
 *
 * @param isSecure If the link is not on any blacklist.
 * @param link The link sent.
 */
@JsonClass(generateAdapter = true)
data class LinkCheckResponse(
    @Json(name = "secure") val isSecure: Boolean,
    @Json(name = "link") val link: String
)
