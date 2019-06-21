package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserInfo
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class UserInfoEndpointTest : ProxerTest() {

    private val expectedInfo = UserInfo(
        id = "121658", username = "RubyGee", image = "121658_cEBC8F.png", isTeamMember = false, isDonator = false,
        status = "Ihr könnt mich jederzeit anschreiben, Skype oder ProxerPn!",
        lastStatusChange = Date(1485372334L * 1000), uploadPoints = 983, forumPoints = 38, animePoints = 4201,
        mangaPoints = 548, infoPoints = 8, miscPoints = 1700
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("user_info.json") {
            api.user
                .info(null, "rubygee")
                .build()
                .execute()
        }

        result shouldEqual expectedInfo
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("user_info.json") {
            api.user.info("123", "rubygee")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/user/userinfo?uid=123&username=rubygee"
    }

    @Test
    fun testUserIdAndUsernameNull() {
        invoking { api.user.info(null, null) } shouldThrow IllegalArgumentException::class
    }
}
