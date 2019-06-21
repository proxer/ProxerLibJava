package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.Bookmark
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class BookmarksEndpointTest : ProxerTest() {

    private val expectedBookmark = Bookmark(
        id = "51851772", entryId = "2727", category = Category.MANGA, name = "The Breaker", episode = 5,
        language = MediaLanguage.ENGLISH, medium = Medium.MANGASERIES, state = MediaState.FINISHED,
        chapterName = "Chapter 05", isAvailable = true
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("bookmarks.json") {
            api.ucp
            .bookmarks()
            .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedBookmark
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("bookmarks.json") {
            api.ucp.bookmarks()
            .category(Category.MANGA)
            .page(12)
            .limit(1)
            .filterAvailable(true)
            .build()
            .execute()
        }

        request.path shouldEqual "/api/v1/ucp/reminder?kat=manga&p=12&limit=1&available=true"
    }
}
