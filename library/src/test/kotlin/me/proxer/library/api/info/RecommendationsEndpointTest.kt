package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Recommendation
import me.proxer.library.enums.Category
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.EnumSet
import java.util.HashSet

class RecommendationsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("recommendations.json")))

        val result = api.info
            .recommendations("17175")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestRecommendation())
    }

    @Test
    fun testManga() {
        server.enqueue(MockResponse().setBody(fromResource("recommendations.json")))

        val result = api.info
            .recommendations("17175")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestRecommendation())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("recommendations.json")))

        api.info.recommendations("17175")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/recommendations?id=17175")
    }

    private fun buildTestRecommendation(): Recommendation {
        return Recommendation(
            "15023", "Boku no Hero Academia", HashSet(listOf("Action")),
            EnumSet.of(FskConstraint.FSK_12), "Izuku ist ein schwächlicher „Normalo“.", Medium.ANIMESERIES,
            13, MediaState.FINISHED, 29150, 3412, 58005, Category.ANIME,
            License.LICENSED, 65, 1, null
        )
    }
}
