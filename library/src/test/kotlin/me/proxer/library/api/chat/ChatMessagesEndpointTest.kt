package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.entity.chat.ChatMessage
import me.proxer.library.enums.ChatMessageAction
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class ChatMessagesEndpointTest : ProxerTest() {

    private val firstExpectedMessage = ChatMessage(
        id = "777191",
        userId = "62",
        username = "genesis",
        image = "62_RvGnYl.png",
        message = "testttt",
        action = ChatMessageAction.NONE,
        date = Date(1523608207L * 1000)
    )

    private val lastExpectedMessage = ChatMessage(
        id = "777189",
        userId = "62",
        username = "genesis",
        image = "62_RvGnYl.png",
        message = "777186",
        action = ChatMessageAction.REMOVE_MESSAGE,
        date = Date(1523608185L * 1000)
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("chat_messages.json") {
            api.chat
                .messages("123")
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo firstExpectedMessage
        result.last() shouldBeEqualTo lastExpectedMessage
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("chat_messages.json") {
            api.chat.messages("12")
                .messageId("21")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/chat/messages?room_id=12&message_id=21"
    }
}
