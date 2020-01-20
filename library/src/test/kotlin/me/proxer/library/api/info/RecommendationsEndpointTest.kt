package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Recommendation
import me.proxer.library.enums.Category
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class RecommendationsEndpointTest : ProxerTest() {

    private val expectedRecommendation = Recommendation(
        id = "15023", name = "Boku no Hero Academia", genres = setOf("Action"),
        fskConstraints = setOf(FskConstraint.FSK_12), description = "Izuku ist ein schwächlicher „Normalo“.",
        medium = Medium.ANIMESERIES, episodeAmount = 13, state = MediaState.FINISHED, ratingSum = 29150,
        ratingAmount = 3412, clicks = 58005, category = Category.ANIME, license = License.LICENSED, positiveVotes = 65,
        negativeVotes = 1, userVote = null
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("recommendations.json") {
            api.info
                .recommendations("17175")
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedRecommendation
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("recommendations.json") {
            api.info.recommendations("17175")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/recommendations?id=17175"
    }
}
