package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DeleteSubscriptionEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("empty.json") {
            api.ucp
                .deleteSubscription("456")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.deleteSubscription("654")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/ucp/deletesubscription"
    }

    @Test
    fun testParameter() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.deleteSubscription("654")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "id=654"
    }
}
