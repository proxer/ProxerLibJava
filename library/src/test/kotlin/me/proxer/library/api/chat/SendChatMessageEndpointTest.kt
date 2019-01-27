package me.proxer.library.api.chat

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SendChatMessageEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.chat
            .sendMessage("123", "message")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.chat.sendMessage("123", "message")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/chat/newmessage")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.chat.sendMessage("312", "someMessage")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("room_id=312&message=someMessage")
    }
}
