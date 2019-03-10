package me.proxer.library.entity.anime

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Entity representing a link to an anime.
 *
 * @property link The actual link. This does point to the HTML embed and not the video itself.
 * @property shouldShowAd If an ad should be shown for this stream. This is part of the Proxers ad system and needs
 *                        special permission to use.
 */
@JsonClass(generateAdapter = true)
data class LinkContainer(
    @Json(name = "link") val link: String,
    @Json(name = "adFlag") val shouldShowAd: Boolean
)
