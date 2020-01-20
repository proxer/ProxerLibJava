package me.proxer.library.api.wiki

import me.proxer.library.ProxerTest
import me.proxer.library.entity.wiki.WikiPage
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class WikiContentEndpointTest : ProxerTest() {

    private val expectedContent = WikiPage(
        "html",
        "<b>Some content</b>"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("wiki_content.json") {
            api.wiki.content("abc")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedContent
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("wiki_content.json") {
            api.wiki.content("abc")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/wiki/content?title=abc"
    }
}
