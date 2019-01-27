package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class CreateConferenceEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("create_conference.json")))

        val result = api.messenger
            .createConference("message", "someUser")
            .build()
            .execute()

        assertThat(result).isEqualTo("abcTest")
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("create_conference.json")))

        api.messenger.createConference("a", "b")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/messenger/newconference")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("create_conference.json")))

        api.messenger.createConference("test", "testUser")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("text=test&username=testUser")
    }
}
