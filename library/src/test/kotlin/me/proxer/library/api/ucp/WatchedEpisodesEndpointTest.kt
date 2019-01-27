package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class WatchedEpisodesEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("watched_episodes.json")))

        val result = api.ucp
            .watchedEpisodes()
            .build()
            .execute()

        assertThat(result).isEqualTo(3243)
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("watched_episodes.json")))

        api.ucp.watchedEpisodes()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/listsum")
    }
}
