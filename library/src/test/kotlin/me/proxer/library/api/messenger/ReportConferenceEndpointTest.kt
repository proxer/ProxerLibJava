package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

class ReportConferenceEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.messenger
                .report("123", "evil user")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.messenger.report("123", "test")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/messenger/report"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("empty.json") {
            api.messenger.report("id", "report")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "conference_id=id&text=report"
    }
}
