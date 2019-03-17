package me.proxer.library.api.anime

import me.proxer.library.ProxerTest
import me.proxer.library.entity.anime.LinkContainer
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VastLinkEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("link_vast.json")))

        val result = api.anime
            .vastLink("4")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestLinkContainer())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("link_vast.json")))

        api.anime.vastLink("9")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/anime/linkvast?id=9")
    }

    private fun buildTestLinkContainer(): LinkContainer {
        return LinkContainer("//www.dailymotion.com/embed/video/k4D1tLdhKG", "https://example.com")
    }
}
