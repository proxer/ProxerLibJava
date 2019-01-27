package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class DeleteBookmarkEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.ucp
            .deleteBookmark("123")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.deleteBookmark("321")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/deletereminder")
    }

    @Test
    fun testParameter() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.deleteBookmark("321")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("id=321")
    }
}
