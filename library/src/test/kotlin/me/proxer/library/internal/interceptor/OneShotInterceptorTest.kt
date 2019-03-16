package me.proxer.library.internal.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.notNull
import org.mockito.Mockito.verify

class OneShotInterceptorTest {

    private val interceptor = OneShotInterceptor()
    private val chain = mock(Interceptor.Chain::class.java)

    @Test
    fun testOneShotSetForRequestsWithBodies() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)
        val body = RequestBody.create(MediaType.parse("text/plain"), "hello")
        val request = Request.Builder().post(body).url("https://proxer.me/fake").build()

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value.body()?.isOneShot).isTrue()
    }

    @Test
    fun testNoModificationForRequestsWithoutBodies() {
        val requestCaptor = ArgumentCaptor.forClass(Request::class.java)
        val request = Request.Builder().url("https://proxer.me/fake").build()

        `when`(chain.request()).thenReturn(request)
        `when`(chain.proceed(notNull())).thenReturn(mock(Response::class.java))

        interceptor.intercept(chain)

        verify(chain).proceed(requestCaptor.capture())
        assertThat(requestCaptor.value).isEqualTo(request)
    }
}
