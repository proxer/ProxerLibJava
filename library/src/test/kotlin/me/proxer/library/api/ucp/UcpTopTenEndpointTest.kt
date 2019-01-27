package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.ucp.UcpTopTenEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UcpTopTenEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_top_ten.json")))

        val result = api.ucp
            .topTen()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("ucp_top_ten.json")))

        api.ucp.topTen()
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/ucp/topten")
    }

    private fun buildTestEntry(): UcpTopTenEntry {
        return UcpTopTenEntry("640467", "2357", "High School DxD", Medium.ANIMESERIES, Category.ANIME)
    }
}
