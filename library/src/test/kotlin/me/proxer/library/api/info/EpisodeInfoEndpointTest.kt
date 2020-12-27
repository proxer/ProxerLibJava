package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.AnimeEpisode
import me.proxer.library.entity.info.EpisodeInfo
import me.proxer.library.entity.info.MangaEpisode
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class EpisodeInfoEndpointTest : ProxerTest() {

    private val expectedEpisodeInfo = EpisodeInfo(
        firstEpisode = 1,
        lastEpisode = 12,
        category = Category.ANIME,
        availableLanguages = setOf(MediaLanguage.GERMAN_SUB, MediaLanguage.ENGLISH_SUB),
        userProgress = 0,
        episodes = listOf(
            AnimeEpisode(
                number = 1,
                language = MediaLanguage.ENGLISH_SUB,
                hosters = setOf("proxer-stream", "mp4upload", "videoweed", "novamov", "streamcloud2"),
                hosterImages = listOf(
                    "proxer-stream.png",
                    "mp4upload.png",
                    "videoweed.png",
                    "novamov.png",
                    "streamcloud.png"
                )
            ),
            AnimeEpisode(
                number = 2,
                language = MediaLanguage.ENGLISH_SUB,
                hosters = setOf("proxer-stream", "mp4upload", "videoweed", "novamov", "streamcloud2"),
                hosterImages = listOf(
                    "proxer-stream.png",
                    "mp4upload.png",
                    "videoweed.png",
                    "novamov.png",
                    "streamcloud.png"
                )
            )
        )
    )

    private val expectedEpisodeInfoManga = EpisodeInfo(
        firstEpisode = 1,
        lastEpisode = 50,
        category = Category.MANGA,
        availableLanguages = setOf(MediaLanguage.ENGLISH),
        userProgress = 12,
        episodes = listOf(
            MangaEpisode(number = 1, language = MediaLanguage.ENGLISH, title = "Chapter 1"),
            MangaEpisode(number = 2, language = MediaLanguage.ENGLISH, title = "Chapter 2")
        )
    )

    private val expectedEpisodeInfoNovel = EpisodeInfo(
        firstEpisode = 1,
        lastEpisode = 50,
        category = Category.NOVEL,
        availableLanguages = setOf(MediaLanguage.ENGLISH),
        userProgress = 12,
        episodes = listOf(
            MangaEpisode(number = 1, language = MediaLanguage.ENGLISH, title = "Chapter 1"),
            MangaEpisode(number = 2, language = MediaLanguage.ENGLISH, title = "Chapter 2")
        )
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("episode_info_anime.json") {
            api.info
                .episodeInfo("1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedEpisodeInfo
    }

    @Test
    fun testManga() {
        val (result, _) = server.runRequest("episode_info_manga.json") {
            api.info
                .episodeInfo("12")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedEpisodeInfoManga
    }

    @Test
    fun testNovel() {
        val (result, _) = server.runRequest("episode_info_novel.json") {
            api.info
                .episodeInfo("12")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedEpisodeInfoNovel
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("episode_info_anime.json") {
            api.info.episodeInfo("17")
                .page(0)
                .limit(10)
                .includeNotAvailableEpisodes(true)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/listinfo?id=17&p=0&limit=10&includeNotAvailableChapters=true"
    }
}
