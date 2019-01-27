package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.enums.MessageAction
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SendMessageEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.messenger
            .sendMessage("123", "message")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.messenger.sendMessage("123", "message")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/setmessage")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.messenger.sendMessage("id", "someMessage")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("conference_id=id&text=someMessage")
    }

    @Test
    fun testActionParameters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.messenger.sendMessage("id", MessageAction.REMOVE_USER, "Testerio")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("conference_id=id&text=%2FremoveUser%20Testerio")
    }

    @Test
    fun testInvalidAction() {
        assertThatThrownBy {
            api.messenger.sendMessage("id", MessageAction.NONE, "")
                .build()
                .execute()
        }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
