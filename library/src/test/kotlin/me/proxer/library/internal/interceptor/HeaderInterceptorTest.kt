package me.proxer.library.internal.interceptor

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import me.proxer.library.BuildConfig.VERSION
import me.proxer.library.ProxerApi
import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.Request
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class HeaderInterceptorTest : ProxerTest() {

    private val interceptor = HeaderInterceptor("mock-key", "mock-user-agent")
    private val requestSlot = slot<Request>()

    private val chain = mockk<Interceptor.Chain>().apply {
        every { proceed(capture(requestSlot)) } returns mockk()
    }

    @Test
    fun testApiKey() {
        val (_, request) = server.runRequest("news.json") {
            api.notifications.news().build().execute()
        }

        request.headers["proxer-api-key"] shouldEqual "mock-key"
    }

    @Test
    fun testDefaultUserAgent() {
        val (_, request) = server.runRequest("news.json") {
            api.notifications.news().build().execute()
        }

        request.headers["User-Agent"] shouldEqual "ProxerLibJava/$VERSION"
    }

    @Test
    fun testApiKeyHeaderNotSetForOtherUrl() {
        every { chain.request() } returns Request.Builder().url(ProxerUrls.cdnBase).build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("proxer-api-key").shouldBeNull()
    }

    @Test
    fun testCorrectHeadersForTestMode() {
        every { chain.request() } returns Request.Builder().url(ProxerUrls.apiBase).build()

        HeaderInterceptor(ProxerApi.TEST_KEY, "mock-user-agent").intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("proxer-api-key").shouldBeNull()
        requestSlot.captured.header("proxer-api-testmode") shouldEqual "1"
    }

    @Test
    fun testTestModeHeaderNotSerForOtherUrl() {
        every { chain.request() } returns Request.Builder().url(ProxerUrls.cdnBase).build()

        HeaderInterceptor(ProxerApi.TEST_KEY, "mock-user-agent").intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("proxer-api-testmode").shouldBeNull()
    }

    @Test
    fun testCorrectHeadersForCdn() {
        every { chain.request() } returns Request.Builder().url(ProxerUrls.cdnBase).build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("User-Agent").toString() shouldEqual "mock-user-agent"
    }

    @Test
    fun testCorrectHeadersForStreamServer() {
        every { chain.request() } returns Request.Builder().url(ProxerUrls.streamBase).build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("User-Agent").toString() shouldEqual "mock-user-agent"
    }

    @Test
    fun testCorrectHeadersForSpecificStreamServer() {
        every { chain.request() } returns Request.Builder().url("https://s3-ps.proxer.me").build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("User-Agent").toString() shouldEqual "mock-user-agent"
    }

    @Test
    fun testCorrectHeadersForMangaServer() {
        every { chain.request() } returns Request.Builder().url("https://manga0.proxer.me").build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("User-Agent").toString() shouldEqual "mock-user-agent"
    }

    @Test
    fun testCorrectHeadersForProxy() {
        every { chain.request() } returns Request.Builder().url("https://proxy.proxer.me").build()

        interceptor.intercept(chain)

        requestSlot.isCaptured shouldBe true
        requestSlot.captured.header("User-Agent").toString() shouldEqual "mock-user-agent"
    }

    @Test
    fun testOtherHostThrows() {
        every { chain.request() } returns Request.Builder().url("https://example.com").build()

        invoking { interceptor.intercept(chain) } shouldThrow IllegalArgumentException::class
    }
}
