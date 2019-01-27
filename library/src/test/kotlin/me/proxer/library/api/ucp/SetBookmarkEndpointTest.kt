package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class SetBookmarkEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.ucp
            .setBookmark("123", 12, MediaLanguage.ENGLISH_SUB, Category.ANIME)
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.setBookmark("123", 12, MediaLanguage.ENGLISH_SUB, Category.ANIME)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/setreminder")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.ucp.setBookmark("321", 7, MediaLanguage.GERMAN, Category.MANGA)
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8()).isEqualTo("id=321&episode=7&language=de&kat=manga")
    }
}
