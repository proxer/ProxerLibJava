package me.proxer.library.api.messenger

import me.proxer.library.ProxerTest
import me.proxer.library.entity.messenger.LinkCheckResponse
import me.proxer.library.runRequest
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class CheckLinkEndpointTest : ProxerTest() {

    private val expectedResponse = LinkCheckResponse(false, "https://proxer.biz")

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("link_check.json") {
            api.messenger.checkLink("https://proxer.biz")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedResponse
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("link_check.json") {
            api.messenger.checkLink("https://proxer.biz".toHttpUrl())
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/messenger/checkLink"
    }

    @Test
    fun testParameter() {
        val (_, request) = server.runRequest("link_check.json") {
            api.messenger.checkLink("https://proxer.biz")
                .build()
                .execute()
        }

        request.body.readUtf8() shouldBeEqualTo "link=https%3A%2F%2Fproxer.biz"
    }
}
