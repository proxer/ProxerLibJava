package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.ConferenceInfo
import me.proxer.library.entity.messenger.ConferenceParticipant
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ConferenceInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("conference_info.json")))

        val result = api.messenger
            .conferenceInfo("1")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestConferenceInfo())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("conference_info.json")))

        api.messenger.conferenceInfo("1")
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/messenger/conferenceinfo?conference_id=1")
    }

    private fun buildTestConferenceInfo(): ConferenceInfo {
        return ConferenceInfo(
            "Ein weiterer Test", 2, Date(1480260735L * 1000),
            Date(1487080743L * 1000), "121658",
            listOf(
                ConferenceParticipant(
                    "121658", "121658_cEBC8F.png", "RubyGee",
                    "Ihr könnt mich jederzeit anschreiben, Skype oder ProxerPn!"
                ),
                ConferenceParticipant(
                    "574520", "574520_K4DfDC.jpg", "Testerio",
                    "Ich bin Rubys Sklave~"
                )
            )
        )
    }
}
