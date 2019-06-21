package me.proxer.library.api.apps

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ErrorLogEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.apps
                .errorLog("3", "test message")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.apps.errorLog("3", "test message")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/apps/errorlog"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.apps.errorLog("3", "test message")
                .anonym(false)
                .silent(false)
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "id=3&message=test%20message&anonym=false&silent=false"
    }
}
