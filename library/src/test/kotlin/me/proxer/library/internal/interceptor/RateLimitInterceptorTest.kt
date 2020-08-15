package me.proxer.library.internal.interceptor

import me.proxer.library.ProxerApi
import me.proxer.library.ProxerException
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.ProxerTest
import me.proxer.library.enums.Language
import me.proxer.library.fromResource
import me.proxer.library.runRequest
import okhttp3.Cache
import okhttp3.mockwebserver.MockResponse
import org.amshove.kluent.AnyException
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBeNull
import org.amshove.kluent.shouldNotThrow
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RateLimitInterceptorTest : ProxerTest() {

    private val rateLimitApi = ProxerApi.Builder("mock-key")
        .enableRateLimitProtection()
        .client(client.newBuilder().cache(Cache(createTempDir(), 1_024L * 1_024L)).build())
        .build()

    @Test
    fun testSingleRequest() {
        server.runRequest("chapter.json") {
            rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
        }
    }

    @Test
    fun testRateLimitError() {
        // Limit 10.
        repeat(8) {
            server.runRequest("chapter.json") {
                rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
            }
        }

        val result = invoking {
            server.runRequest("chapter.json") {
                rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
            }
        } shouldThrow ProxerException::class

        result.exception.errorType shouldBe ProxerException.ErrorType.SERVER
        result.exception.serverErrorType shouldBe ServerErrorType.RATE_LIMIT
    }

    @Test
    fun testCachedResponse() {
        val response = MockResponse()
            .setBody(fromResource("chapter.json"))
            .setHeader("Cache-Control", "public, max-age=31536000")

        // Limit 10.
        repeat(8) {
            server.enqueue(response)
            rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
        }

        invoking {
            server.enqueue(response)
            rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
        } shouldNotThrow AnyException
    }

    @Test
    @RepeatedTest(50)
    fun testParallelRequests() {
        // Limit 10.
        repeat(7) {
            server.runRequest("chapter.json") {
                rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
            }
        }

        server.enqueue(MockResponse().setBody(fromResource("chapter.json")))
        server.enqueue(MockResponse().setBody(fromResource("chapter.json")))

        val lock = CountDownLatch(2)
        var error: ProxerException? = null

        rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().enqueue(
            { lock.countDown() },
            {
                error = it
                lock.countDown()
            }
        )

        rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().enqueue(
            { lock.countDown() },
            {
                error = it
                lock.countDown()
            }
        )

        lock.await(3_000L, TimeUnit.MILLISECONDS)

        error.shouldNotBeNull()
        error!!.serverErrorType shouldBe ServerErrorType.RATE_LIMIT
    }

    @Test
    @Disabled // Does not work because of real urls being passed in and mock urls in cache.
    fun testLimitButCached() {
        // Limit 10.
        repeat(7) {
            server.runRequest("chapter.json") {
                rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
            }
        }

        val response = MockResponse()
            .setBody(fromResource("chapter.json"))
            .setHeader("Cache-Control", "public, max-age=31536000")

        server.enqueue(response)
        rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()

        invoking {
            server.enqueue(response)
            rateLimitApi.manga.chapter("123", 123, Language.ENGLISH).build().execute()
        } shouldNotThrow AnyException
    }

    @Test
    @Disabled
    fun testResetStateAfterTimeLimit() {
        // Limit 30.
        repeat(28) {
            server.runRequest("chat_messages.json") {
                rateLimitApi.chat.messages("123").build().execute()
            }
        }

        val result = invoking {
            server.runRequest("chat_messages.json") {
                rateLimitApi.chat.messages("123").build().execute()
            }
        } shouldThrow ProxerException::class

        result.exception.serverErrorType shouldBe ServerErrorType.RATE_LIMIT

        Thread.sleep(30_000)

        server.runRequest("chat_messages.json") {
            rateLimitApi.chat.messages("123").build().execute()
        }
    }
}
