package me.proxer.library.entity.anime

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Entity representing a link to an anime.
 *
 * @property link The actual link. This does point to the HTML embed and not the video itself.
 * @property adTag The VAST tag of the ad to show. Is empty if no ad is available or none should be shown.
 */
@JsonClass(generateAdapter = true)
data class LinkContainer(
    @Json(name = "link") val link: String,
    @Json(name = "adTag") val adTag: String
)
