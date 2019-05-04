package me.proxer.library.internal.interceptor

import me.proxer.library.BuildConfig.VERSION
import me.proxer.library.ProxerApi
import me.proxer.library.ProxerTest
import me.proxer.library.fromResource
import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.notNull
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * @author Ruben Gees
 */
class HeaderInterceptorTest : ProxerTest() {

    private val interceptor = HeaderInterceptor("mock-key", "mock-user-agent")
    private val chain = mock(Interceptor.Chain::class.java)

    @Test
    fun testApiKey() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.notifications.news().build().execute()

        assertThat(server.takeRequest().headers.get("proxer-api-key")).isEqualTo("mock-key")
    }

    @Test
    fun testDefaultUserAgent() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.notifications.news().build().execute()

        assertThat(server.takeRequest().headers.get("User-Agent")).isEqualTo("ProxerLibJava/$VERSION")
    }

    @Test
    fun testApiKeyHeaderNotSetForOtherUrl() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url(ProxerUrls.cdnBase).build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("proxer-api-key")).isNull()
    }

    @Test
    fun testCorrectHeadersForTestMode() {
        val testInterceptor = HeaderInterceptor(ProxerApi.TEST_KEY, "mock-user-agent")
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url(ProxerUrls.apiBase).build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        testInterceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("proxer-api-key")).isNull()
        assertThat(requestCaptor.value.header("proxer-api-testmode")).isEqualTo("1")
    }

    @Test
    fun testTestModeHeaderNotSerForOtherUrl() {
        val testInterceptor = HeaderInterceptor(ProxerApi.TEST_KEY, "mock-user-agent")
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url(ProxerUrls.cdnBase).build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        testInterceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("proxer-api-testmode")).isNull()
    }

    @Test
    fun testCorrectHeadersForCdn() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url(ProxerUrls.cdnBase).build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("User-Agent").toString()).isEqualTo("mock-user-agent")
    }

    @Test
    fun testCorrectHeadersForStreamServer() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url(ProxerUrls.streamBase).build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("User-Agent").toString()).isEqualTo("mock-user-agent")
    }

    @Test
    fun testCorrectHeadersForSpecificStreamServer() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("https://s3-ps.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("User-Agent").toString()).isEqualTo("mock-user-agent")
    }

    @Test
    fun testCorrectHeadersForMangaServer() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("https://manga0.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("User-Agent").toString()).isEqualTo("mock-user-agent")
    }

    @Test
    fun testCorrectHeadersForProxy() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("https://proxy.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.header("User-Agent").toString()).isEqualTo("mock-user-agent")
    }

    @Test
    fun testOtherHostThrows() {
        `when`(chain.request()).thenReturn(Request.Builder().url("https://example.com").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { interceptor.intercept(chain) }
    }
}
