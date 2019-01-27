package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Industry
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import me.proxer.library.fromResource
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class IndustryEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("industry.json")))

        val result = api.info
            .industry("1")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestIndustry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("industry.json")))

        api.info.industry("3")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/industry?id=3")
    }

    private fun buildTestIndustry(): Industry {
        return Industry(
            "123", "Hoods Entertainment", IndustryType.STUDIO, Country.JAPAN,
            HttpUrl.parse("http://www.hoods.co.jp/"),
            "Anfang 2009 gegründetes Animations-Studio mit Sitz in Tokio."
        )
    }
}
