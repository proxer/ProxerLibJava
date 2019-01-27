package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class ModifyUserInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.info
            .note("123")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.info.note("321")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/setuserinfo")
    }

    @Test
    fun testNoteParameter() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.info.note("321")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("id=321&type=note")
    }

    @Test
    fun testFavoriteParameter() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.info.markAsFavorite("321")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("id=321&type=favor")
    }

    @Test
    fun testFinishedParameter() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.info.markAsFinished("321")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("id=321&type=finish")
    }
}
