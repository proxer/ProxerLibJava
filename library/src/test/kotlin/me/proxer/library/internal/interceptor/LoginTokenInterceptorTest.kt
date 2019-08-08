package me.proxer.library.internal.interceptor

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import me.proxer.library.ProxerException
import me.proxer.library.ProxerException.ErrorType
import me.proxer.library.ProxerTest
import me.proxer.library.internal.DefaultLoginTokenManager
import me.proxer.library.runRequest
import me.proxer.library.runRequestIgnoringError
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * @author Ruben Gees
 */
class LoginTokenInterceptorTest : ProxerTest() {

    @Test
    fun testTokenSetAfterLogin() {
        server.runRequest("login.json") { api.user.login("test", "secret").build().execute() }

        val (_, request) = server.runRequest("news.json") { api.notifications.news().build().execute() }

        request.headers.get("proxer-api-token") shouldEqual """
            OmSjyOzMeyICUnErDD04lsDta7REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUo
            D8XGHOHP11Uc46rF4BSXrZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKT
            sYBOslW61fOwFFDI7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6
        """.trimIndent().replace("\n", "")
    }

    @Test
    fun testTokenRemovedAfterLogout() {
        server.runRequest("login.json") { api.user.login("test", "secret").build().execute() }
        server.runRequest("logout.json") { api.user.logout().build().execute() }

        val (_, request) = server.runRequest("news.json") { api.notifications.news().build().execute() }

        request.headers.get("proxer-api-token").shouldBeNull()
    }

    @Test
    fun testTokenNotSetOnError() {
        server.runRequestIgnoringError("login_error.json") {
            api.user.login("test", "secret").build().execute()
        }

        val (_, request) = server.runRequest("news.json") { api.notifications.news().build().execute() }

        request.headers.get("proxer-api-token").shouldBeNull()
    }

    @Test
    fun testTokenNotRemovedOnError() {
        server.runRequest("login.json") { api.user.login("test", "secret").build().execute() }
        server.runRequestIgnoringError("news.json") { api.notifications.news().build().execute() }

        val (_, request) = server.runRequest("news.json") {
            api.notifications.news().build().execute()
        }

        request.headers.get("proxer-api-token") shouldEqual """
            OmSjyOzMeyICUnErDD04lsDta7REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUo
            D8XGHOHP11Uc46rF4BSXrZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKT
            sYBOslW61fOwFFDI7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6
        """.trimIndent().replace("\n", "")
    }

    @Test
    fun testTokenRemovedOnLoginError() {
        server.runRequest("login.json") { api.user.login("test", "secret").build().execute() }
        server.runRequestIgnoringError("conferences_error.json") { api.messenger.conferences().build().execute() }

        val (_, request) = server.runRequest("news.json") {
            api.notifications.news().build().execute()
        }

        request.headers.get("proxer-api-token").shouldBeNull()
    }

    @Test
    fun testMalformedResponse() {
        server.runRequest("login_malformed.json") {
            val result = invoking {
                api.user.login("test", "secret").build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.PARSING
        }
    }

    @Test
    fun testTokenWithErrorResponse() {
        server.runRequestIgnoringError("login_token_and_error.json") {
            api.user.login("test", "secret").build().execute()
        }

        val (_, request) = server.runRequest("news.json") { api.notifications.news().build().execute() }

        request.headers.get("proxer-api-token").shouldBeNull()
    }

    @Test
    fun testEmptyResponse() {
        server.runRequest(MockResponse()) {
            val result = invoking {
                api.user.login("test", "secret").build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.IO
        }
    }

    @Test
    fun testNoBodyResponse() {
        val (_, request) = server.runRequest(MockResponse()) {
            client.newCall(Request.Builder().url("https://proxer.me/fake").build()).execute()
        }

        request.headers.get("proxer-api-token").shouldBeNull()
    }

    @Test
    fun testTokenNotSetForLogin() {
        server.runRequest("login.json") { api.user.login("test", "secret").build().execute() }

        val (_, request) = server.runRequest("login.json") {
            api.user.login("test", "secret").build().execute()
        }

        request.getHeader("proxer-api-token").shouldBeNull()
    }

    @ParameterizedTest(name = "{1}")
    @CsvSource(
        "http://cdn.proxer.me, Cdn Host",
        "http://s3-ps.proxer.me, Stream Host",
        "http://manga0.proxer.me, Manga Host",
        "https://example.com, Invalid Host"
    )
    fun testTokenNotSetFor(input: String, @Suppress("UNUSED_PARAMETER") name: String) {
        val tokenManager = DefaultLoginTokenManager()
        val interceptor = spyk(LoginTokenInterceptor(tokenManager))
        val chain = mockk<Interceptor.Chain>()

        val request = Request.Builder().url(input).build()
        val modifiedRequestSlot = slot<Request>()
        val response = mockk<Response>()

        tokenManager.persist("mock-token")

        every { chain.request() } returns request
        every { chain.proceed(capture(modifiedRequestSlot)) } returns response

        interceptor.intercept(chain)

        verify(exactly = 1) { chain.proceed(request) }
        modifiedRequestSlot.isCaptured shouldBe true
        modifiedRequestSlot.captured.header("proxer-api-token").shouldBeNull()
    }
}
