package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.Bookmark
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
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

        result.first() shouldBeEqualTo expectedBookmark
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("bookmarks.json") {
            api.ucp.bookmarks()
                .page(12)
                .limit(1)
                .name("test")
                .category(Category.MANGA)
                .filterAvailable(true)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/ucp/reminder?p=12&limit=1&name=test&kat=manga&available=true"
    }
}
