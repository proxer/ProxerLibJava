package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ReportChatMessageEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.chat
                .reportMessage("123", "message")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.chat.reportMessage("123", "message")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/chat/reportmessage"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.chat.reportMessage("312", "someMessage")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "message_id=312&message=someMessage"
    }
}
