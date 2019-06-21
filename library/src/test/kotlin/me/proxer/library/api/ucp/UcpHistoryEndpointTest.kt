package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpHistoryEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import me.proxer.library.toProxerDateTime
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UcpHistoryEndpointTest : ProxerTest() {

    private val expectedEntry = UcpHistoryEntry(
        id = "407661627", entryId = "14701", name = "Nejimaki Seirei Senki: Tenkyou no Alderamin",
        language = MediaLanguage.ENGLISH_SUB, medium = Medium.ANIMESERIES, category = Category.ANIME, episode = 1,
        date = "2017-03-13 23:16:36".toProxerDateTime()
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("ucp_history.json") {
            api.ucp
                .history()
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("ucp_history.json") {
            api.ucp.history()
                .page(0)
                .limit(10)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/ucp/history?p=0&limit=10"
    }
}
