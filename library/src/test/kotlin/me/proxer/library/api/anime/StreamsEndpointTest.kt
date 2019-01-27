package me.proxer.library.api.anime

import me.proxer.library.ProxerTest
import me.proxer.library.entity.anime.Stream
import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class StreamsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("streams.json")))

        val result = api.anime
            .streams("12", 1, AnimeLanguage.GERMAN_SUB)
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestStream())
    }

    @Test
    fun testOfficial() {
        server.enqueue(MockResponse().setBody(fromResource("streams.json")))

        val result = api.anime
            .streams("3", 4, AnimeLanguage.GERMAN_SUB)
            .build()
            .execute()

        assertThat(result).element(2).isEqualTo(buildOfficialTestStream())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("streams.json")))

        api.anime.streams("33", 3, AnimeLanguage.GERMAN_DUB)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/anime/streams?id=33&episode=3&language=gerdub")
    }

    @Test
    fun testProxerStreamPath() {
        server.enqueue(MockResponse().setBody(fromResource("streams.json")))

        api.anime.streams("35", 1, AnimeLanguage.ENGLISH_DUB)
            .includeProxerStreams(true)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/anime/proxerstreams?id=35&episode=1&language=engdub")
    }

    @Test
    fun testProxerStreamFalse() {
        server.enqueue(MockResponse().setBody(fromResource("streams.json")))

        api.anime.streams("43", 3, AnimeLanguage.GERMAN_SUB)
            .includeProxerStreams(false)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/anime/streams?id=43&episode=3&language=gersub")
    }

    private fun buildTestStream(): Stream {
        return Stream(
            "565484", "dailymotion", "Dailymotion", "dailymotion.png",
            "217857", "kollenbad", Date(1454414911L * 1000), "698",
            "Grim-Subs", false, false
        )
    }

    private fun buildOfficialTestStream(): Stream {
        return Stream(
            "565924", "novamov", "Auroravid/Novamov", "novamov.png",
            "66", "ProxerBot", Date(1454767407L * 1000), "92",
            "Chinurarete-Subs", true, true
        )
    }
}
