package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpTopTenEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UcpTopTenEndpointTest : ProxerTest() {

    private val expectedEntry = UcpTopTenEntry(
        id = "640467", entryId = "2357", name = "High School DxD", medium = Medium.ANIMESERIES,
        category = Category.ANIME
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("ucp_top_ten.json") {
            api.ucp
                .topTen()
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("ucp_top_ten.json") {
            api.ucp.topTen()
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/ucp/topten"
    }
}
