package me.proxer.library.api.anime

import me.proxer.library.ProxerTest
import me.proxer.library.entity.anime.Stream
import me.proxer.library.enums.AnimeLanguage
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class StreamsEndpointTest : ProxerTest() {

    private val expectedTestStream = Stream(
        id = "565484", hoster = "dailymotion", hosterName = "Dailymotion", image = "dailymotion.png",
        uploaderId = "217857", uploaderName = "kollenbad", date = Date(1454414911L * 1000), translatorGroupId = "698",
        translatorGroupName = "Grim-Subs", isOfficial = false, isPublic = false
    )

    private val expectedOfficialTestStream = Stream(
        id = "565924", hoster = "novamov", hosterName = "Auroravid/Novamov", image = "novamov.png", uploaderId = "66",
        uploaderName = "ProxerBot", date = Date(1454767407L * 1000), translatorGroupId = "92",
        translatorGroupName = "Chinurarete-Subs", isOfficial = true, isPublic = true
    )

    @Test
    fun testDefault() {
        val (result) = server.runRequest("streams.json") {
            api.anime
                .streams("12", 1, AnimeLanguage.GERMAN_SUB)
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedTestStream
    }

    @Test
    fun testOfficial() {
        val (result, _) = server.runRequest("streams.json") {
            api.anime
                .streams("3", 4, AnimeLanguage.GERMAN_SUB)
                .build()
                .safeExecute()
        }

        result[2] shouldEqual expectedOfficialTestStream
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("streams.json") {
            api.anime.streams("33", 3, AnimeLanguage.GERMAN_DUB)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/anime/streams?id=33&episode=3&language=gerdub"
    }

    @Test
    fun testProxerStreamPath() {
        val (_, request) = server.runRequest("streams.json") {
            api.anime.streams("35", 1, AnimeLanguage.ENGLISH_DUB)
                .includeProxerStreams(true)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/anime/proxerstreams?id=35&episode=1&language=engdub"
    }

    @Test
    fun testProxerStreamFalse() {
        val (_, request) = server.runRequest("streams.json") {
            api.anime.streams("43", 3, AnimeLanguage.GERMAN_SUB)
                .includeProxerStreams(false)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/anime/streams?id=43&episode=3&language=gersub"
    }
}
