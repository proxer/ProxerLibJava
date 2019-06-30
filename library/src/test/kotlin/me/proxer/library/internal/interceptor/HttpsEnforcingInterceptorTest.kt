package me.proxer.library.internal.interceptor

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import okhttp3.Interceptor
import okhttp3.Request
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

/**
 * @author Ruben Gees
 */
class HttpsEnforcingInterceptorTest {

    private val interceptor = HttpsEnforcingInterceptor()
    private val requestSlot = slot<Request>()

    private val chain = mockk<Interceptor.Chain>().apply {
        every { proceed(capture(requestSlot)) } returns mockk()
    }

    @ParameterizedTest(name = "{2}")
    @CsvSource(
        "http://proxer.me, https://proxer.me/, Web",
        "http://cdn.proxer.me, https://cdn.proxer.me/, Cdn",
        "http://manga1.proxer.me, https://manga1.proxer.me/, Manga",
        "http://stream.proxer.me, https://stream.proxer.me/, Stream Html",
        "http://s1-ps.proxer.me, https://s1-ps.proxer.me/, Stream Alternative",
        "http://proxy.proxer.me, https://proxy.proxer.me/, Proxy",
        "https://proxer.me, https://proxer.me/, Already Https"
    )
    fun testHttpsUpgrade(input: String, expected: String, @Suppress("UNUSED_PARAMETER") name: String) {
        every { chain.request() } returns Request.Builder().url(input).build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.url.toString() shouldEqual expected
    }

    @Test
    fun testOtherHostThrows() {
        every { chain.request() } returns Request.Builder().url("https://example.com").build()

        invoking { interceptor.intercept(chain) } shouldThrow IllegalArgumentException::class
    }
}
