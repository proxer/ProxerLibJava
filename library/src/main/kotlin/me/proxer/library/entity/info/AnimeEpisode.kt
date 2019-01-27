package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.MediaLanguage

/**
 * Entity holding the data of a single anime episode.
 *
 * @property hosters The names of the available hosters.
 * @property hosterImages The images of the available hosters.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class AnimeEpisode(
    override val number: Int,
    override val language: MediaLanguage,
    @Json(name = "types") val hosters: Set<String>,
    @Json(name = "typeimages") val hosterImages: List<String>
) : Episode(number, language)
