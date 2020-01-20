package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.ConferenceInfo
import me.proxer.library.entity.messenger.ConferenceParticipant
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ConferenceInfoEndpointTest : ProxerTest() {

    private val expectedInfo = ConferenceInfo(
        topic = "Ein weiterer Test", participantAmount = 2, firstMessageTime = Date(1480260735L * 1000),
        lastMessageTime = Date(1487080743L * 1000), leaderId = "121658",
        participants = listOf(
            ConferenceParticipant(
                id = "121658", image = "121658_cEBC8F.png", username = "RubyGee",
                status = "Ihr könnt mich jederzeit anschreiben, Skype oder ProxerPn!"
            ),
            ConferenceParticipant(
                id = "574520", image = "574520_K4DfDC.jpg", username = "Testerio",
                status = "Ich bin Rubys Sklave~"
            )
        )
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("conference_info.json") {
            api.messenger
            .conferenceInfo("1")
            .build()
            .execute()
        }

        result shouldBeEqualTo expectedInfo
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("conference_info.json") {
            api.messenger.conferenceInfo("1")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/messenger/conferenceinfo?conference_id=1"
    }
}
