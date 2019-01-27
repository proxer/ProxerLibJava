package me.proxer.library.api.apps

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ErrorLogEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        val result = api.apps
            .errorLog("3", "test message")
            .build()
            .execute()

        assertThat(result).isNull()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.apps.errorLog("3", "test message")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/apps/errorlog")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("empty.json")))

        api.apps.errorLog("3", "test message")
            .anonym(false)
            .silent(false)
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8())
            .isEqualTo("id=3&message=test%20message&anonym=false&silent=false")
    }
}
