package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Industry
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import me.proxer.library.runRequest
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class IndustryEndpointTest : ProxerTest() {

    private val expectedIndustry = Industry(
        id = "123", name = "Hoods Entertainment", type = IndustryType.STUDIO, country = Country.JAPAN,
        link = "http://www.hoods.co.jp/".toHttpUrlOrNull(),
        description = "Anfang 2009 gegründetes Animations-Studio mit Sitz in Tokio."
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("industry.json") {
            api.info
                .industry("1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedIndustry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("industry.json") {
            api.info.industry("3")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/industry?id=3"
    }
}
