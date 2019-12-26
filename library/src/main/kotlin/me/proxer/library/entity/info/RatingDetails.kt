package me.proxer.library.entity.info

import com.squareup.moshi.Json

/**
 * Container for the single ratings. This ranges from 1 to 5 and is 0 if the user has not rated a
 * specific (or all) section(s) yet.
 *
 * Note: This is not annotated with @JsonClass(generateAdapter = true), because we have our own adapter.
 *
 * @property genre The genre rating.
 * @property story The story rating.
 * @property animation The animation rating.
 * @property characters The characters rating.
 * @property music The music rating.
 *
 * @author Desnoo
 */
data class RatingDetails(
    @Json(name = "genre") val genre: Int,
    @Json(name = "story") val story: Int,
    @Json(name = "animation") val animation: Int,
    @Json(name = "characters") val characters: Int,
    @Json(name = "music") val music: Int
)
