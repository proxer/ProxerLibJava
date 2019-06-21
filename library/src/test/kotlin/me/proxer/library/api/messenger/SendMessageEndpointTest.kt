package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.enums.MessageAction
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SendMessageEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.messenger
                .sendMessage("123", "message")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.messenger.sendMessage("123", "message")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/messenger/setmessage"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.messenger.sendMessage("id", "someMessage")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "conference_id=id&text=someMessage"
    }

    @Test
    fun testActionParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.messenger.sendMessage("id", MessageAction.REMOVE_USER, "Testerio")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "conference_id=id&text=%2FremoveUser%20Testerio"
    }

    @Test
    fun testInvalidAction() {
        invoking {
            api.messenger.sendMessage("id", MessageAction.NONE, "")
                .build()
                .execute()
        } shouldThrow IllegalArgumentException::class
    }
}
