package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserInfo
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class UserInfoEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_info.json")))

        val result = api.user
            .info(null, "rubygee")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestInfo())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_info.json")))

        api.user.info("123", "rubygee")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/user/userinfo?uid=123&username=rubygee")
    }

    @Test
    fun testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { api.user.info(null, null) }
    }

    private fun buildTestInfo(): UserInfo {
        return UserInfo(
            "121658", "RubyGee", "121658_cEBC8F.png", false, false,
            "Ihr könnt mich jederzeit anschreiben, Skype oder ProxerPn!", Date(1485372334L * 1000),
            983, 38, 4201, 548, 8, 1700
        )
    }
}
