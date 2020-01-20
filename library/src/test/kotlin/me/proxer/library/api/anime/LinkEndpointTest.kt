package me.proxer.library.api.anime

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LinkEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("link.json") {
            api.anime
                .link("17")
                .build()
                .execute()
        }

        result shouldBeEqualTo "//www.dailymotion.com/embed/video/k4D1tfdhKG"
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("link.json") {
            api.anime
                .link("13")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/anime/link?id=13"
    }
}
