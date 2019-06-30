package me.proxer.library.internal.interceptor

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeTrue
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class OneShotInterceptorTest {

    private val interceptor = OneShotInterceptor()
    private val requestSlot = slot<Request>()

    private val chain = mockk<Interceptor.Chain>().apply {
        every { proceed(capture(requestSlot)) } returns mockk()
    }

    @Test
    fun testOneShotSetForRequestsWithBodies() {
        val body = "hello".toRequestBody("text/plain".toMediaTypeOrNull())
        val request = Request.Builder().post(body).url("https://proxer.me/fake").build()

        every { chain.request() } returns request

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.body!!.shouldNotBeNull()
        requestSlot.captured.body!!.isOneShot().shouldBeTrue()
    }

    @Test
    fun testNoModificationForRequestsWithoutBodies() {
        val request = Request.Builder().url("https://proxer.me/fake").build()

        every { chain.request() } returns request

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured shouldEqual request
    }
}
