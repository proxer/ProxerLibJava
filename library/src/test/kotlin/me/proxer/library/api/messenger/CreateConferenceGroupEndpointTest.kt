package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class CreateConferenceGroupEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("create_conference.json")))

        val result = api.messenger
            .createConferenceGroup("topic", "message", listOf("someUser", "anotherUser"))
            .build()
            .execute()

        assertThat(result).isEqualTo("abcTest")
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("create_conference.json")))

        api.messenger.createConferenceGroup("a", "b", listOf("user"))
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/messenger/newconferencegroup")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("create_conference.json")))

        api.messenger
            .createConferenceGroup(
                "topic", "message",
                listOf("someUser", "anotherUser", "testUser")
            )
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8())
            .isEqualTo("topic=topic&text=message&users=someUser&users=anotherUser&users=testUser")
    }
}
