package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.TranslatorGroup
import me.proxer.library.enums.Country
import me.proxer.library.fromResource
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TranslatorGroupEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group.json")))

        val result = api.info
            .translatorGroup("4")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestGroup())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group.json")))

        api.info.translatorGroup("12")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/translatorgroup?id=12")
    }

    private fun buildTestGroup(): TranslatorGroup {
        return TranslatorGroup(
            "11", "Gruppe Kampfkuchen", Country.GERMANY,
            "http://i.imgur.com/hBoT4Ax.png", HttpUrl.parse("http://kampfkuchen.de"),
            "Gruppe Kampfkuchen, die Gruppe für qualitativ hochwertige Lolisubs! Qualität vor "
                + "Quantität! Besucht uns doch mal in #Kampfkuchen auf irc.otakubox.de!\r\n\r\nGK ist "
                + "mittlerweile drei Jahre alt! Schaut doch mal bei uns im Forum oder auf der Page vorbei."
                + "\r\n\r\nWir sind auch immer froh um Hilfe beim Subben. Wer also denkt, er könne was oder "
                + "wer gerne was lernen möchte, darf sich ruhig bei uns im Chan melden. Wir beißen nicht... "
                + "Jedenfalls mindestens einen Tag im Jahr nicht.",
            5087, 23
        )
    }
}
