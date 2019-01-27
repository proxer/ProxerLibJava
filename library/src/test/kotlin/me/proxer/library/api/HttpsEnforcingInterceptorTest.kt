package me.proxer.library.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.notNull
import org.mockito.Mockito.verify

class HttpsEnforcingInterceptorTest {

    private val interceptor = HttpsEnforcingInterceptor()
    private val chain = mock(Interceptor.Chain::class.java)

    @Test
    fun testHttpsUpgradeWeb() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("http://proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.url().toString()).isEqualTo("https://proxer.me/")
    }

    @Test
    fun testHttpsUpgradeCdn() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("http://cdn.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.url().toString()).isEqualTo("https://cdn.proxer.me/")
    }

    @Test
    fun testHttpsUpgradeManga() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("http://manga1.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.url().toString()).isEqualTo("https://manga1.proxer.me/")
    }

    @Test
    fun testHttpsUpgradeStreamHtml() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("http://stream.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.url().toString()).isEqualTo("https://stream.proxer.me/")
    }

    @Test
    fun testHttpsUpgradeStreamAlternative() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("http://s1-ps.proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.url().toString()).isEqualTo("https://s1-ps.proxer.me/")
    }

    @Test
    fun testHttpsUntouched() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)

        `when`(chain.request()).thenReturn(Request.Builder().url("https://proxer.me").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.url().toString()).isEqualTo("https://proxer.me/")
    }

    @Test
    fun testOtherHostThrows() {
        `when`(chain.request()).thenReturn(Request.Builder().url("https://example.com").build())
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy { interceptor.intercept(chain) }
    }
}
