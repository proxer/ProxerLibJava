package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.User
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class LoginEndpointTest : ProxerTest() {

    private val expectedUser = User(
        id = "121658", image = "121658_VHuZqz.jpg", isTeamMember = true, isDonator = true,
        loginToken = """
            OmSjyOzMeyICUnErDD04lsDta7REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUo
            D8XGHOHP11Uc46rF4BSXrZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKT
            sYBOslW61fOwFFDI7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6
        """.trimIndent().replace("\n", "")
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("login.json") {
            api.user.login("test", "supersecret")
                .build()
                .execute()
        }

        result shouldEqual expectedUser
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("login.json") {
            api.user.login("test", "supersecret")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/user/login"
    }

    @Test
    fun testParameters() {
        val (_, request) = server.runRequest("login.json") {
            api.user.login("test", "supersecret")
                .secretKey("secretkey")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldEqual "username=test&password=supersecret&secretkey=secretkey"
    }
}
