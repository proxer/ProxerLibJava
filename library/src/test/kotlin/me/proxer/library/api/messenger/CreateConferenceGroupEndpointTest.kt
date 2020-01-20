package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
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

        result shouldBeEqualTo "abcTest"
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("create_conference.json") {
            api.messenger.createConferenceGroup("a", "b", listOf("user"))
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/messenger/newconferencegroup"
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

        request.body.readUtf8() shouldBeEqualTo """
                topic=topic&text=message&users%5B%5D=someUser&users%5B%5D=anotherUser&users%5B%5D=testUser
            """.trimIndent().replace("\n", "")
    }
}
