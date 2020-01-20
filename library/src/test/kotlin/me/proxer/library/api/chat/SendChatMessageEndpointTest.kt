package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SendChatMessageEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.chat
                .sendMessage("123", "message")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.chat.sendMessage("123", "message")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/chat/newmessage"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.chat.sendMessage("312", "someMessage")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "room_id=312&message=someMessage"
    }
}
