package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage

/**
 * Entity containing information about the available episodes or chapters of an [Entry].
 *
 * @property firstEpisode The first available episode.
 * @property lastEpisode The last available episode.
 * @property category The category of the associated media entry.
 * @property availableLanguages The available languages.
 * @property userProgress The progress, the user has made on this media entry so far.
 * @property episodes The actual list of episodes.
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class EpisodeInfo(
    @Json(name = "start") val firstEpisode: Int,
    @Json(name = "end") val lastEpisode: Int,
    @Json(name = "kat") val category: Category,
    @Json(name = "lang") val availableLanguages: Set<MediaLanguage>,
    @Json(name = "state") val userProgress: Int,
    @Json(name = "episodes") val episodes: List<Episode>
)
