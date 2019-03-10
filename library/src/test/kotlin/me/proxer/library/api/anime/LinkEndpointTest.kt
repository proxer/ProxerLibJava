package me.proxer.library.api.anime

import me.proxer.library.ProxerTest
import me.proxer.library.entity.anime.LinkContainer
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LinkEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("link.json")))

        val result = api.anime
            .link("17")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestLink())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("link.json")))

        api.anime.link("13")
            .enableAds(true)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/anime/link2?id=13&adFlag=1")
    }

    private fun buildTestLink(): LinkContainer {
        return LinkContainer("//www.dailymotion.com/embed/video/k4D1tfdhKG", true)
    }
}
