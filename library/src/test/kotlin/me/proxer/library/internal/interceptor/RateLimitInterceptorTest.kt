package me.proxer.library.internal.interceptor

import me.proxer.library.ProxerApi
import me.proxer.library.ProxerException
import me.proxer.library.ProxerException.ServerErrorType
import me.proxer.library.ProxerTest
import me.proxer.library.enums.Language
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class RateLimitInterceptorTest : ProxerTest() {

    private val rateLimitApi = ProxerApi.Builder("mock-key")
        .enableRateLimitProtection()
        .client(client)
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
