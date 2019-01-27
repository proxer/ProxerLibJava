package me.proxer.library.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson
import me.proxer.library.api.EpisodeInfoAdapter.IntermediateEpisodeInfo.IntermediateEpisode
import me.proxer.library.entity.info.AnimeEpisode
import me.proxer.library.entity.info.EpisodeInfo
import me.proxer.library.entity.info.MangaEpisode
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage

/**
 * @author Ruben Gees
 */
internal class EpisodeInfoAdapter {

    private companion object {
        private const val DELIMITER = ","
    }

    @FromJson
    fun fromJson(json: IntermediateEpisodeInfo): EpisodeInfo {
        val episodes = when (json.category) {
            Category.ANIME -> json.episodes.map { episode ->
                val hosters = episode.hosters?.split(DELIMITER)?.toSet()
                    ?: throw IllegalArgumentException("episode.hosters is null")

                val hosterImages = episode.hosterImages?.split(DELIMITER)
                    ?: throw IllegalArgumentException("episode.hosters is null")

                AnimeEpisode(episode.number, episode.language, hosters, hosterImages)
            }
            Category.MANGA -> json.episodes.map { episode ->
                val title = episode.title ?: throw IllegalArgumentException("episode.title is null")

                MangaEpisode(episode.number, episode.language, title)
            }
        }

        return EpisodeInfo(
            json.firstEpisode, json.lastEpisode, json.category, json.availableLanguages,
            json.userProgress, episodes
        )
    }

    @ToJson
    fun toJson(value: EpisodeInfo?): IntermediateEpisodeInfo? {
        if (value == null) return null

        val episodes = value.episodes.map {
            when (it) {
                is AnimeEpisode -> {
                    val joinedHosters = it.hosters.joinToString(DELIMITER)
                    val joinedHosterImages = it.hosterImages.joinToString(DELIMITER)

                    IntermediateEpisode(it.number, it.language, null, joinedHosters, joinedHosterImages)
                }
                is MangaEpisode -> IntermediateEpisode(it.number, it.language, it.title, null, null)
                else -> throw IllegalArgumentException("Unknown Episode type: ${it.javaClass.name}")
            }
        }

        return IntermediateEpisodeInfo(
            value.firstEpisode, value.lastEpisode, value.category, value.availableLanguages,
            value.userProgress, episodes
        )
    }

    @JsonClass(generateAdapter = true)
    internal class IntermediateEpisodeInfo(
        @Json(name = "start") val firstEpisode: Int,
        @Json(name = "end") val lastEpisode: Int,
        @Json(name = "kat") val category: Category,
        @Json(name = "lang") val availableLanguages: Set<MediaLanguage>,
        @Json(name = "state") val userProgress: Int,
        @Json(name = "episodes") val episodes: List<IntermediateEpisode>
    ) {

        @JsonClass(generateAdapter = true)
        internal class IntermediateEpisode(
            @Json(name = "no") val number: Int,
            @Json(name = "typ") val language: MediaLanguage,
            @Json(name = "title") val title: String?,
            @Json(name = "types") val hosters: String?,
            @Json(name = "typeimg") val hosterImages: String?
        )
    }
}
