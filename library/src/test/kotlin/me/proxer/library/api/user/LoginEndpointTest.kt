package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.User
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LoginEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))

        val result = api.user.login("test", "supersecret")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestUser())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))

        api.user.login("test", "supersecret")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/user/login")
    }

    @Test
    fun testParameters() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))

        api.user.login("test", "supersecret")
            .secretKey("secretkey")
            .build()
            .execute()

        assertThat(server.takeRequest().body.readUtf8())
            .isEqualTo("username=test&password=supersecret&secretkey=secretkey")
    }

    private fun buildTestUser(): User {
        return User(
            "121658", "121658_VHuZqz.jpg", true, true,
            "OmSjyOzMeyICUnErDD04lsDta7REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCY"
                    + "cdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXrZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlw"
                    + "K9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwFFDI7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6"
        )
    }
}
