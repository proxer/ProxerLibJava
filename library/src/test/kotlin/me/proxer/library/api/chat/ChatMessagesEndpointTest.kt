package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.api.ProxerException
import me.proxer.library.entity.chat.ChatMessage
import me.proxer.library.enums.ChatMessageAction
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.IOException
import java.util.Date

/**
 * @author Ruben Gees
 */
class ChatMessagesEndpointTest : ProxerTest() {

    @Test
    @Throws(IOException::class, ProxerException::class)
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("chat_messages.json")))

        val result = api.chat
            .messages("123")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildFirstMessage())
        assertThat(result).last().isEqualTo(buildLastMessage())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("chat_messages.json")))

        api.chat.messages("12")
            .messageId("21")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/chat/messages?room_id=12&message_id=21")
    }

    private fun buildFirstMessage(): ChatMessage {
        return ChatMessage(
            "777191", "62", "genesis", "62_RvGnYl.png",
            "testttt", ChatMessageAction.NONE, Date(1523608207L * 1000)
        )
    }

    private fun buildLastMessage(): ChatMessage {
        return ChatMessage(
            "777189", "62", "genesis", "62_RvGnYl.png",
            "777186", ChatMessageAction.REMOVE_MESSAGE, Date(1523608185L * 1000)
        )
    }
}
