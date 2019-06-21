package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DeleteCommentEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest(bodyFile = "empty.json") {
            api.ucp
                .deleteComment("123")
                .build()
                .execute()
        }

        result.shouldBeNull()
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.deleteComment("321")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/ucp/deletecomment"
    }

    @Test
    fun testParameter() {
        val (_, request) = server.runRequest("empty.json") {
            api.ucp.deleteComment("321")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "id=321"
    }
}
