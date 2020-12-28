package me.proxer.library

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.amshove.kluent.AnyException
import org.amshove.kluent.coInvoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.with
import org.junit.jupiter.api.Test

class KotlinExtensionsKtTest : ProxerTest() {

    @Test
    fun testAwait(): Unit = runBlocking {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        coInvoking { api.notifications.news().build().await() } shouldNotThrow AnyException
    }

    @Test
    fun testAwaitWithError(): Unit = runBlocking {
        server.enqueue(MockResponse().setBody(fromResource("news.json")).setResponseCode(404))

        coInvoking { api.notifications.news().build().await() } shouldThrow ProxerException::class with
            { errorType shouldBe ProxerException.ErrorType.IO }
    }

    @Test
    fun testCancel(): Unit = runBlocking {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        with(async { api.notifications.news().build().await() }) {
            cancel()

            coInvoking { await() } shouldThrow CancellationException::class
        }

        server.requestCount shouldBeEqualTo 0
    }
}
