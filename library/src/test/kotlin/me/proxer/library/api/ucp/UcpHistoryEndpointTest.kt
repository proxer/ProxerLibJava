package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpHistoryEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * @author Ruben Gees
 */
class UcpHistoryEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_history.json")))

        val result = api.ucp
            .history()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_history.json")))

        api.ucp.history()
            .page(0)
            .limit(10)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/history?p=0&limit=10")
    }

    private fun buildTestEntry(): UcpHistoryEntry {
        return UcpHistoryEntry(
            "407661627", "14701", "Nejimaki Seirei Senki: Tenkyou no Alderamin",
            MediaLanguage.ENGLISH_SUB, Medium.ANIMESERIES, Category.ANIME, 1,
            SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2017-03-13 23:16:36")
        )
    }
}
