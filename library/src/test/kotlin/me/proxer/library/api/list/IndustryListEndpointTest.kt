package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.IndustryCore
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class IndustryListEndpointTest : ProxerTest() {

    private val expectedIndustry = IndustryCore(
        id = "493", name = "10Gauge", type = IndustryType.STUDIO, country = Country.JAPAN
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("industry_list.json") {
            api.list
                .industryList()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedIndustry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("industry_list.json") {
            api.list.industryList()
                .country(Country.GERMANY)
                .search("test")
                .searchStart("test2")
                .page(3)
                .limit(17)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/list/industrys?start=test2&contains=test&country=de&p=3&limit=17"
    }
}
