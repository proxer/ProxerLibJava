package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.IndustryCore
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class IndustryListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("industry_list.json")))

        val result = api.list
            .industryList()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestGroup())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("industry_list.json")))

        api.list.industryList()
            .country(Country.GERMANY)
            .search("test")
            .searchStart("test2")
            .page(3)
            .limit(17)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/industrys?start=test2&contains=test&country=de&p=3&limit=17")
    }

    private fun buildTestGroup(): IndustryCore {
        return IndustryCore("493", "10Gauge", IndustryType.STUDIO, Country.JAPAN)
    }
}
