package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.Message
import me.proxer.library.enums.Device
import me.proxer.library.enums.MessageAction
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class MessagesEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("messages.json")))

        val result = api.messenger
            .messages()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestMessage())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("messages.json")))

        api.messenger.messages()
            .conferenceId("123")
            .messageId("321")
            .markAsRead(false)
            .build()
            .execute()

        Assertions.assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/messenger/messages?conference_id=123&message_id=321&read=false")
    }

    private fun buildTestMessage(): Message {
        return Message(
            "5193325", "131029", "121658", "RubyGee", "RubyGee",
            MessageAction.ADD_USER, Date(1480260735L * 1000), Device.UNSPECIFIED
        )
    }
}
