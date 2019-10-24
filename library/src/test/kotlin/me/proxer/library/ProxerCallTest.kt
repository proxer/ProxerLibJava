package me.proxer.library

import io.mockk.every
import io.mockk.mockk
import me.proxer.library.ProxerException.ErrorType
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.entity.notifications.NewsArticle
import me.proxer.library.internal.ProxerResponse
import net.jodah.concurrentunit.Waiter
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.SocketPolicy
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

/**
 * @author Ruben Gees
 */
class ProxerCallTest : ProxerTest() {

    @Test
    fun testTimeoutError() {
        val body = MockResponse().setBody(fromResource("news.json"))
        val response = body.apply { body.socketPolicy = SocketPolicy.NO_RESPONSE }

        server.runRequest(response) {
            val result = invoking {
                api.notifications.news().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.TIMEOUT
        }
    }

    @Test
    fun testIOError() {
        server.runRequest(MockResponse().setResponseCode(404)) {
            val result = invoking {
                api.notifications.news().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.IO
            result.exceptionMessage shouldEqual "Unsuccessful request: 404"
        }
    }

    @Test
    fun testIOErrorWithBody() {
        server.runRequest(MockResponse().setBody("Error!").setResponseCode(404)) {
            val result = invoking {
                api.notifications.news().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.IO
            result.exceptionMessage shouldEqual "Unsuccessful request: 404"
        }
    }

    @Test
    fun testInternalServerError() {
        server.runRequest(MockResponse().setResponseCode(500)) {
            val result = invoking {
                api.notifications.news().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.SERVER
            result.exception.serverErrorType shouldBe ServerErrorType.UNKNOWN
        }
    }

    @Test
    fun testInvalidEncodingError() {
        val response = MockResponse().setBody(fromResource("news.json").replace(":", "invalid"))

        server.runRequest(response) {
            val result = invoking {
                api.notifications.news().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.IO
        }
    }

    @Test
    fun testInvalidDataError() {
        val response = MockResponse().setBody(fromResource("news.json").replace("256", "invalid"))

        server.runRequest(response) {
            val result = invoking {
                api.notifications.news().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.PARSING
        }
    }

    @Test
    fun testServerError() {
        server.runRequest("conferences_error.json") {
            val result = invoking {
                api.messenger.conferences().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.SERVER
            result.exception.serverErrorType shouldBe ServerErrorType.MESSAGES_LOGIN_REQUIRED
            result.exceptionMessage shouldEqual "Du bist nicht eingeloggt."
        }
    }

    @Test
    fun testServerErrorWithMessage() {
        server.runRequest("ucp_settings_error.json") {
            val result = invoking {
                api.ucp.setSettings().build().execute()
            } shouldThrow ProxerException::class

            result.exception.errorType shouldBe ErrorType.SERVER
            result.exception.serverErrorType shouldBe ServerErrorType.UCP_INVALID_SETTINGS
            result.exceptionMessage shouldEqual "Ungültige Eingabe für Felder.\n[profil]"
        }
    }

    @Test
    fun testUnknownError() {
        val internalCall = mockk<Call<ProxerResponse<String>>>()
        val call = ProxerCall(internalCall)
        val error = IllegalStateException()

        every { internalCall.execute() } throws error

        val result = invoking { call.execute() } shouldThrow ProxerException::class

        result.exception.errorType shouldBe ErrorType.UNKNOWN
        result.exceptionCause.shouldBe(error)
    }

    @Test
    fun testSafeExecute() {
        val response = mockk<ProxerResponse<List<NewsArticle>>>().apply {
            every { isSuccessful } returns true
            every { data } returns emptyList()
        }

        val internalResponse = mockk<Response<ProxerResponse<List<NewsArticle>>>>().apply {
            every { isSuccessful } returns true
            every { body() } returns response
        }

        val internalCall = mockk<Call<ProxerResponse<List<NewsArticle>>>>()
        val call = ProxerCall(internalCall)

        every { internalCall.execute() } returns internalResponse

        call.execute().shouldNotBeNull()
    }

    @Test
    fun testSafeExecuteNull() {
        val response = mockk<ProxerResponse<List<NewsArticle>>>().apply {
            every { isSuccessful } returns true
            every { data } returns null
        }

        val internalResponse = mockk<Response<ProxerResponse<List<NewsArticle>>>>().apply {
            every { isSuccessful } returns true
            every { body() } returns response
        }

        val internalCall = mockk<Call<ProxerResponse<List<NewsArticle>>>>()
        val call = ProxerCall(internalCall)

        every { internalCall.execute() } returns internalResponse

        val result = invoking { call.safeExecute() } shouldThrow ProxerException::class

        result.exception.errorType shouldBe ErrorType.UNKNOWN
        result.exceptionCause shouldBeInstanceOf NullPointerException::class
    }

    @Test
    fun testEnqueue() {
        val waiter = Waiter()

        server.runRequest("news.json") {
            api.notifications.news().build().enqueue(
                {
                    waiter.assertTrue(it != null && it.isNotEmpty())
                    waiter.resume()
                },
                { waiter.fail(it) }
            )
        }

        waiter.await(1_000)
    }

    @Test
    fun testEnqueueError() {
        val waiter = Waiter()

        server.runRequest(MockResponse().setBody(fromResource("news.json")).setResponseCode(404)) {
            api.notifications.news().build().enqueue(
                { waiter.fail("Expected error") },
                { exception ->
                    waiter.assertEquals(ErrorType.IO, exception.errorType)
                    waiter.resume()
                }
            )
        }

        waiter.await(1_000)
    }

    @Test
    fun testIsExecuted() {
        server.runRequest("news.json") {
            val call = api.notifications.news().build().apply {
                execute()
            }

            call.isExecuted shouldBe true
        }
    }

    @Test
    fun testCancel() {
        val waiter = Waiter()
        val call = api.notifications.news().build()

        call.enqueue(
            { waiter.fail("Expected error") },
            { exception ->
                waiter.assertEquals(ErrorType.CANCELLED, exception.errorType)
                waiter.resume()
            }
        )

        call.cancel()

        waiter.await(1_000)

        call.isCanceled shouldBe true
    }

    @Test
    fun testClone() {
        val call = api.notifications.news().build()

        server.runRequest("news.json") { call.execute() }

        val (result, _) = server.runRequest("news.json") { call.clone().execute() }

        result.shouldNotBeNull()
    }

    @Test
    fun testRequest() {
        api.notifications.news().build().request().shouldNotBeNull()
    }

    @Test
    fun testRequestThrowingErrorWithoutMessage() {
        val internalCall = mockk<Call<ProxerResponse<String>>>()
        val call = ProxerCall(internalCall)
        val error = IOException()

        every { internalCall.execute() } throws error

        val result = invoking { call.execute() } shouldThrow ProxerException::class

        result.exception.errorType shouldBe ErrorType.IO
        result.exceptionCause shouldBe error
    }
}
