package me.proxer.library.internal.interceptor

import me.proxer.library.ProxerException
import me.proxer.library.ProxerException.ErrorType
import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import me.proxer.library.internal.DefaultLoginTokenManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.notNull
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

/**
 * @author Ruben Gees
 */
class LoginTokenInterceptorTest : ProxerTest() {

    @Test
    fun testTokenSetAfterLogin() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.user.login("test", "secret").build().execute()
        api.notifications.news().build().execute()

        server.takeRequest()

        assertThat(server.takeRequest().headers.get("proxer-api-token")).isEqualTo(
            "OmSjyOzMeyICUnErDD04lsDta7" +
                "REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXr" +
                "ZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwFFDI" +
                "7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6"
        )
    }

    @Test
    fun testTokenRemovedAfterLogout() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))
        server.enqueue(MockResponse().setBody(fromResource("logout.json")))
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.user.login("test", "secret").build().execute()
        api.user.logout().build().execute()
        api.notifications.news().build().execute()

        server.takeRequest()
        server.takeRequest()

        assertThat(server.takeRequest().headers.get("proxer-api-token")).isNull()
    }

    @Test
    fun testTokenNotSetOnError() {
        server.enqueue(MockResponse().setBody(fromResource("login_error.json")))
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        try {
            api.user.login("test", "secret").build().execute()
        } catch (error: ProxerException) {
        }

        api.notifications.news().build().execute()

        server.takeRequest()

        assertThat(server.takeRequest().headers.get("proxer-api-token")).isNull()
    }

    @Test
    fun testTokenNotRemovedOnError() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))
        server.enqueue(MockResponse().setBody(fromResource("logout_error.json")))
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.user.login("test", "secret").build().execute()

        try {
            api.user.logout().build().execute()
        } catch (error: ProxerException) {
        }

        api.notifications.news().build().execute()

        server.takeRequest()
        server.takeRequest()

        assertThat(server.takeRequest().headers.get("proxer-api-token")).isEqualTo(
            "OmSjyOzMeyICUnErDD04lsDta7REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvT" +
                "UoD8XGHOHP11Uc46rF4BSXrZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83r" +
                "l6VPD9bqXabkKTsYBOslW61fOwFFDI7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6"
        )
    }

    @Test
    fun testTokenRemovedOnLoginError() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))
        server.enqueue(MockResponse().setBody(fromResource("conferences_error.json")))
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.user.login("test", "secret").build().execute()

        try {
            api.messenger.conferences().build().execute()
        } catch (error: ProxerException) {
        }

        api.notifications.news().build().execute()

        server.takeRequest()
        server.takeRequest()

        assertThat(server.takeRequest().headers.get("proxer-api-token")).isNull()
    }

    @Test
    fun testMalformedResponse() {
        server.enqueue(MockResponse().setBody(fromResource("login_malformed.json")))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.user.login("test", "secret").build().execute() }
            .matches { exception -> exception.errorType === ErrorType.PARSING }
    }

    @Test
    fun testEmptyResponse() {
        server.enqueue(MockResponse())

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.user.login("test", "secret").build().execute() }
            .matches { exception -> exception.errorType === ErrorType.PARSING }
    }

    @Test
    fun testNoBodyResponse() {
        server.enqueue(MockResponse())

        client.newCall(Request.Builder().url("https://proxer.me/fake").build()).execute()

        assertThat(server.takeRequest().headers.get("proxer-api-token")).isNull()
    }

    @Test
    fun testTokenNotSetForLogin() {
        server.enqueue(MockResponse().setBody(fromResource("login.json")))
        server.enqueue(MockResponse().setBody(fromResource("login.json")))

        api.user.login("test", "secret").build().execute()
        api.user.login("test", "secret").build().execute()

        server.takeRequest()

        assertThat(server.takeRequest().getHeader("proxer-api-token")).isNull()
    }

    @Test
    fun testTokenNotSetForInvalidHost() {
        val tokenManager = DefaultLoginTokenManager()
        val interceptor = spy(LoginTokenInterceptor(tokenManager))
        val chain = mock(Interceptor.Chain::class.java)

        val request = Request.Builder().url("https://example.com").build()
        val response = mock(Response::class.java)

        tokenManager.persist("mock-token")

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(notNull())).thenReturn(response)

        interceptor.intercept(chain)

        verify(chain).proceed(request)
    }

    @Test
    fun testTokenNotSetForCdnHost() {
        val tokenManager = DefaultLoginTokenManager()
        val interceptor = spy(LoginTokenInterceptor(tokenManager))
        val chain = mock(Interceptor.Chain::class.java)

        val request = Request.Builder().url("http://cdn.proxer.me").build()
        val response = mock(Response::class.java)

        tokenManager.persist("mock-token")

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(notNull())).thenReturn(response)

        interceptor.intercept(chain)

        verify(chain).proceed(request)
    }

    @Test
    fun testTokenNotSetForStreamHost() {
        val tokenManager = DefaultLoginTokenManager()
        val interceptor = spy(LoginTokenInterceptor(tokenManager))
        val chain = mock(Interceptor.Chain::class.java)

        val request = Request.Builder().url("http://s3-ps.proxer.me").build()
        val response = mock(Response::class.java)

        tokenManager.persist("mock-token")

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(notNull())).thenReturn(response)

        interceptor.intercept(chain)

        verify(chain).proceed(request)
    }

    @Test
    fun testTokenNotSetForMangaHost() {
        val tokenManager = DefaultLoginTokenManager()
        val interceptor = spy(LoginTokenInterceptor(tokenManager))
        val chain = mock(Interceptor.Chain::class.java)

        val request = Request.Builder().url("http://manga0.proxer.me").build()
        val response = mock(Response::class.java)

        tokenManager.persist("mock-token")

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(notNull())).thenReturn(response)

        interceptor.intercept(chain)

        verify(chain).proceed(request)
    }
}
