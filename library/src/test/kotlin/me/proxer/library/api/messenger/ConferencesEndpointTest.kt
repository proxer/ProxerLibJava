package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.Conference
import me.proxer.library.enums.ConferenceType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ConferencesEndpointTest : ProxerTest() {

    private val expectedConference = Conference(
        id = "133663", topic = "Novus4K", customTopic = "", participantAmount = 2, image = "513596_5DoIo2.png",
        imageType = "avatar", isGroup = false, isRead = true, date = Date(1488810726L * 1000),
        unreadMessageAmount = 0, lastReadMessageId = "0"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("conferences.json") {
            api.messenger
                .conferences()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedConference
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("conferences.json") {
            api.messenger.conferences()
                .page(0)
                .type(ConferenceType.GROUP)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/messenger/conferences?p=0&type=group"
    }
}
