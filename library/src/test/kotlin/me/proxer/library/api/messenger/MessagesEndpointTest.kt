package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.Message
import me.proxer.library.enums.Device
import me.proxer.library.enums.MessageAction
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class MessagesEndpointTest : ProxerTest() {

    private val expectedMessage = Message(
        id = "5193325", conferenceId = "131029", userId = "121658", username = "RubyGee", message = "RubyGee",
        action = MessageAction.ADD_USER, date = Date(1480260735L * 1000), device = Device.UNSPECIFIED
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("messages.json") {
            api.messenger
                .messages()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedMessage
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("messages.json") {
            api.messenger.messages()
                .conferenceId("123")
                .messageId("321")
                .markAsRead(false)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/messenger/messages?conference_id=123&message_id=321&read=false"
    }
}
