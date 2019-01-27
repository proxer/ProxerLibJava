package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.AnimeEpisode
import me.proxer.library.entity.info.EpisodeInfo
import me.proxer.library.entity.info.MangaEpisode
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Arrays
import java.util.EnumSet

/**
 * @author Ruben Gees
 */
class EpisodeInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("episode_info_anime.json")))

        val result = api.info
            .episodeInfo("1")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestInfo())
    }

    @Test
    fun testManga() {
        server.enqueue(MockResponse().setBody(fromResource("episode_info_manga.json")))

        val result = api.info
            .episodeInfo("12")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestInfoManga())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("episode_info_anime.json")))

        api.info.episodeInfo("17")
            .page(0)
            .limit(10)
            .includeNotAvailableEpisodes(true)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/info/listinfo?id=17&p=0&limit=10&includeNotAvailableChapters=true")
    }

    private fun buildTestInfo(): EpisodeInfo {
        return EpisodeInfo(
            1, 12, Category.ANIME,
            EnumSet.of(MediaLanguage.GERMAN_SUB, MediaLanguage.ENGLISH_SUB), 0,
            listOf(
                AnimeEpisode(
                    1, MediaLanguage.ENGLISH_SUB,
                    setOf("proxer-stream", "mp4upload", "videoweed", "novamov", "streamcloud2"),
                    listOf("proxer-stream.png", "mp4upload.png", "videoweed.png", "novamov.png", "streamcloud.png")
                ),
                AnimeEpisode(
                    2, MediaLanguage.ENGLISH_SUB,
                    setOf("proxer-stream", "mp4upload", "videoweed", "novamov", "streamcloud2"),
                    listOf("proxer-stream.png", "mp4upload.png", "videoweed.png", "novamov.png", "streamcloud.png")
                )
            )
        )
    }

    private fun buildTestInfoManga(): EpisodeInfo {
        return EpisodeInfo(
            1, 50, Category.MANGA,
            EnumSet.of(MediaLanguage.ENGLISH), 12, Arrays.asList(
                MangaEpisode(1, MediaLanguage.ENGLISH, "Chapter 1"),
                MangaEpisode(2, MediaLanguage.ENGLISH, "Chapter 2")
            )
        )
    }
}
