package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.Bookmark
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class BookmarksEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("bookmarks.json")))

        val result = api.ucp
            .bookmarks()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestBookmark())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("bookmarks.json")))

        api.ucp.bookmarks()
            .category(Category.MANGA)
            .page(12)
            .limit(1)
            .filterAvailable(true)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/reminder?kat=manga&p=12&limit=1&available=true")
    }

    private fun buildTestBookmark(): Bookmark {
        return Bookmark(
            "51851772", "2727", Category.MANGA, "The Breaker", 5,
            MediaLanguage.ENGLISH, Medium.MANGASERIES, MediaState.FINISHED, "Chapter 05", true
        )
    }
}
