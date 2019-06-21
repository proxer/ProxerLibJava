package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class CreateConferenceGroupEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("create_conference.json") {
            api.messenger
                .createConferenceGroup("topic", "message", listOf("someUser", "anotherUser"))
                .build()
                .execute()
        }

        result shouldEqual "abcTest"
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("create_conference.json") {
            api.messenger.createConferenceGroup("a", "b", listOf("user"))
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/newconferencegroup"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("create_conference.json") {
            api.messenger
                .createConferenceGroup(
                    topic = "topic", firstMessage = "message",
                    participants = listOf("someUser", "anotherUser", "testUser")
                )
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "topic=topic&text=message&users=someUser&users=anotherUser&users=testUser"
    }
}
