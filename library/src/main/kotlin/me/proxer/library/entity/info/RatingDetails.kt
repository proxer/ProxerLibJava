package me.proxer.library.entity.info

import com.squareup.moshi.Json

/**
 * Container for the single ratings. This ranges from 1 to 5 and is 0 if the user has not rated a
 * specific (or all) section(s) yet.
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
    @Json(name = "genre") val genre: Int = 0,
    @Json(name = "story") val story: Int = 0,
    @Json(name = "animation") val animation: Int = 0,
    @Json(name = "characters") val characters: Int = 0,
    @Json(name = "music") val music: Int = 0
)
