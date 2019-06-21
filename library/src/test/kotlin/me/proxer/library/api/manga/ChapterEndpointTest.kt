package me.proxer.library.api.manga

import me.proxer.library.ProxerTest
import me.proxer.library.entity.manga.Chapter
import me.proxer.library.entity.manga.Page
import me.proxer.library.enums.Language
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ChapterEndpointTest : ProxerTest() {

    private val expectedChapter = Chapter(
        id = "1954", entryId = "2784", title = "Chapter 1", uploaderId = "0", uploaderName = "nobody",
        date = Date(1318716000L * 1000), scanGroupId = null, scanGroupName = null, server = "0",
        pages = listOf(
            Page(name = "fairy-001-01.jpg", height = 1100, width = 765),
            Page(name = "fairy-001-02-03.jpg", height = 641, width = 900)
        )
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("chapter.json") {
            api.manga
                .chapter("15", 1, Language.ENGLISH)
                .build()
                .execute()
        }

        result shouldEqual expectedChapter
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("chapter.json") {
            api.manga.chapter("4523", 1, Language.GERMAN)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/manga/chapter?id=4523&episode=1&language=de"
    }
}
