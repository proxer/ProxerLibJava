package me.proxer.library

import me.proxer.library.ProxerException.ErrorType
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.entity.notifications.NewsArticle
import me.proxer.library.internal.ProxerResponse
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.SocketPolicy
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.time.Duration
import java.util.concurrent.CountDownLatch

/**
 * @author Ruben Gees
 */
class ProxerCallTest : ProxerTest() {

    @Test
    fun testTimeoutError() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")).setSocketPolicy(SocketPolicy.NO_RESPONSE))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.notifications.news().build().execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.TIMEOUT },
                "Exception should have the TIMEOUT ErrorType"
            )
    }

    @Test
    fun testIOError() {
        server.enqueue(MockResponse().setResponseCode(404))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.notifications.news().build().execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.IO },
                "Exception should have the IO ErrorType"
            )
            .matches(
                { exception -> "Unsuccessful request: 404" == exception.message },
                "Exception should have message with HTTP error code"
            )
    }

    @Test
    fun testIOErrorWithBody() {
        server.enqueue(MockResponse().setBody("error!").setResponseCode(404))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.notifications.news().build().execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.IO },
                "Exception should have the IO ErrorType"
            )
            .matches(
                { exception -> "Unsuccessful request: 404" == exception.message },
                "Exception should have message with HTTP error code"
            )
    }

    @Test
    fun testInvalidEncodingError() {
        server.enqueue(MockResponse().setBody(fromResource("news.json").replace(":", "invalid")))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.notifications.news().build().execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.IO },
                "Exception should have the IO ErrorType"
            )
    }

    @Test
    fun testInvalidDataError() {
        server.enqueue(MockResponse().setBody(fromResource("news.json").replace("256", "invalid")))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.notifications.news().build().execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.PARSING },
                "Exception should have the PARSING ErrorType"
            )
    }

    @Test
    fun testServerError() {
        server.enqueue(MockResponse().setBody(fromResource("conferences_error.json")))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.messenger.conferences().build().execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.SERVER },
                "Exception should have the SERVER ErrorType"
            )
            .matches(
                { exception -> exception.serverErrorType === ServerErrorType.MESSAGES_LOGIN_REQUIRED },
                "Exception should have the MESSAGES_LOGIN_REQUIRED ServerErrorType"
            )
            .matches(
                { exception -> exception.message != null && exception.message == "Du bist nicht eingeloggt." },
                "Exception should have the correct message"
            )
    }

    @Test
    fun testServerErrorWithMessage() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_settings_error.json")))

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { api.ucp.setSettings().build().execute() }
            .matches(
                { exception ->
                    exception.message != null && exception.message == "Ungültige Eingabe für Felder.\n[profil]"
                },
                "Exception should have the correct message"
            )
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testUnknownError() {
        val internalCall = mock(Call::class.java) as Call<ProxerResponse<String>>
        val call = ProxerCall(internalCall)

        `when`(internalCall.execute()).thenThrow(IllegalStateException::class.java)

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { call.execute() }
            .matches(
                { exception -> exception.errorType === ErrorType.UNKNOWN },
                "Exception should have the UNKNOWN ErrorType"
            )
            .withCauseExactlyInstanceOf(IllegalStateException::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testSafeExecute() {
        val internalCall = mock(Call::class.java) as Call<ProxerResponse<List<NewsArticle>>>
        val call = ProxerCall(internalCall)
        val internalResponse = mock(Response::class.java) as Response<ProxerResponse<List<NewsArticle>>>
        val response = mock(ProxerResponse::class.java) as ProxerResponse<List<NewsArticle>>

        `when`(internalResponse.isSuccessful).thenReturn(true)
        `when`(response.isSuccessful).thenReturn(true)
        `when`(internalResponse.body()).thenReturn(response)
        `when`(response.data).thenReturn(emptyList())
        `when`(internalCall.execute()).thenReturn(internalResponse)

        assertThat(call.safeExecute()).isNotNull
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testSafeExecuteNull() {
        val internalCall = mock(Call::class.java) as Call<ProxerResponse<String>>
        val call = ProxerCall(internalCall)
        val internalResponse = mock(Response::class.java) as Response<ProxerResponse<String>>
        val response = mock(ProxerResponse::class.java) as ProxerResponse<String>

        `when`(internalResponse.isSuccessful).thenReturn(true)
        `when`(response.isSuccessful).thenReturn(true)
        `when`(internalResponse.body()).thenReturn(response)
        `when`(response.data).thenReturn(null)
        `when`(internalCall.execute()).thenReturn(internalResponse)

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { call.safeExecute() }
            .matches(
                { exception -> exception.errorType === ErrorType.UNKNOWN },
                "Exception should have the UNKNOWN ErrorType"
            )
            .withCauseExactlyInstanceOf(NullPointerException::class.java)
    }

    @Test
    fun testEnqueue() {
        val lock = CountDownLatch(1)

        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.notifications.news().build().enqueue(
            { lock.countDown() },
            { /* Failed. The lock will never be counted down and timeout. */ }
        )

        assertTimeout(Duration.ofSeconds(1000)) { lock.await() }
    }

    @Test
    fun testEnqueueError() {
        val lock = CountDownLatch(1)

        server.enqueue(MockResponse().setBody(fromResource("news.json")).setResponseCode(404))

        api.notifications.news().build().enqueue(
            { /* Failed. The lock will never be counted down and timeout. */ },
            { exception ->
                if (exception.errorType === ErrorType.IO) {
                    lock.countDown()
                } else {
                    // Failed: Not the exception we want. The lock will never be counted down and timeout.
                }
            }
        )

        assertTimeout(Duration.ofSeconds(1000)) { lock.await() }
    }

    @Test
    fun testIsExecuted() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        val call = api.notifications.news().build()

        call.execute()

        assertThat(call.isExecuted).isTrue()
    }

    @Test
    fun testCancel() {
        val lock = CountDownLatch(1)

        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        val call = api.notifications.news().build()

        call.enqueue(
            { /* Failed. The lock will never be counted down and timeout. */ },
            { exception ->
                assertThat(exception.errorType).isEqualTo(ErrorType.CANCELLED)

                lock.countDown()
            }
        )

        call.cancel()

        assertTimeout(Duration.ofSeconds(1000)) { lock.await() }
    }

    @Test
    fun testIsCanceled() {
        val lock = CountDownLatch(1)

        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        val call = api.notifications.news().build()

        call.enqueue(
            { /* Failed. The lock will never be counted down and timeout. */ },
            { lock.countDown() }
        )

        call.cancel()

        assertTimeout(Duration.ofSeconds(1000)) { lock.await() }
        assertThat(call.isCanceled).isTrue()
    }

    @Test
    fun testClone() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        val call = api.notifications.news().build()

        call.execute()

        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        assertThat(call.clone().execute()).isNotNull
    }

    @Test
    fun testRequest() {
        assertThat(api.notifications.news().build().request()).isNotNull
    }

    @Suppress("UNCHECKED_CAST")
    @Test
    fun testRequestThrowingErrorWithoutMessage() {
        val mockedCall = mock(Call::class.java) as Call<ProxerResponse<String>>
        val call = ProxerCall(mockedCall)

        `when`(mockedCall.execute()).thenThrow(IOException())

        assertThatExceptionOfType(ProxerException::class.java)
            .isThrownBy { call.execute() }
            .withCauseExactlyInstanceOf(IOException::class.java)
            .matches { it -> it.errorType === ErrorType.IO }
    }
}
