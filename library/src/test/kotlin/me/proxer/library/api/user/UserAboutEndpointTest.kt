package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserAbout
import me.proxer.library.enums.Gender
import me.proxer.library.enums.RelationshipStatus
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserAboutEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_about.json")))

        val result = api.user
            .about(null, "rubygee")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestAbout())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_about.json")))

        api.user.about("123", "rubygee")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/user/about?uid=123&username=rubygee")
    }

    @Test
    fun testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { api.user.info(null, null) }
    }

    private fun buildTestAbout(): UserAbout {
        return UserAbout(
            "", "Developer", "Anime", "A City", "Some Country",
            "<p>Hello there", "", "", "", "", "skypeTest",
            "", "0000-06-02", Gender.MALE, RelationshipStatus.UNKNOWN
        )
    }
}
