package me.proxer.library.api.anime

import me.proxer.library.ProxerTest
import me.proxer.library.entity.anime.LinkContainer
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class VastLinkEndpointTest : ProxerTest() {

    private val expectedLinkContainer = LinkContainer(
        link = "//www.dailymotion.com/embed/video/k4D1tLdhKG",
        adTag = "https://example.com"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("link_vast.json") {
            api.anime
                .vastLink("4")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedLinkContainer
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("link_vast.json") {
            api.anime.vastLink("9")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/anime/linkvast?id=9"
    }
}
