package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LogoutEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("logout.json")))

        api.user.logout()
            .build()
            .execute()
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("logout.json")))

        api.user.logout()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/user/logout")
    }
}
