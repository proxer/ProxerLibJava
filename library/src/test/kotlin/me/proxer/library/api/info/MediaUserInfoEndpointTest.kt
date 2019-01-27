package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.MediaUserInfo
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class MediaUserInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("media_user_info.json")))

        val result = api.info
            .userInfo("123")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestUserInfo())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("media_user_info.json")))

        api.info.userInfo("321")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/userinfo?id=321")
    }

    private fun buildTestUserInfo(): MediaUserInfo {
        return MediaUserInfo(false, true, false, true)
    }
}
