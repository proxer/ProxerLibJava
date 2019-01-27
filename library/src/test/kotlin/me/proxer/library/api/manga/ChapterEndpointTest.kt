package me.proxer.library.api.manga

import me.proxer.library.ProxerTest
import me.proxer.library.entity.manga.Chapter
import me.proxer.library.entity.manga.Page
import me.proxer.library.enums.Language
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ChapterEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("chapter.json")))

        val result = api.manga
            .chapter("15", 1, Language.ENGLISH)
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestChapter())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("chapter.json")))

        api.manga.chapter("4523", 1, Language.GERMAN)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/manga/chapter?id=4523&episode=1&language=de")
    }

    private fun buildTestChapter(): Chapter {
        return Chapter(
            "1954", "2784", "Chapter 1", "0", "nobody",
            Date(1318716000L * 1000), null, null, "0",
            listOf(
                Page("fairy-001-01.jpg", 1100, 765),
                Page("fairy-001-02-03.jpg", 641, 900)
            )
        )
    }
}
