package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class CreateConferenceEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("create_conference.json") {
            api.messenger
                .createConference("message", "someUser")
                .build()
                .execute()
        }

        result shouldEqual "abcTest"
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("create_conference.json") {
            api.messenger.createConference("a", "b")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/newconference"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("create_conference.json") {
            api.messenger.createConference("test", "testUser")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "text=test&username=testUser"
    }
}
