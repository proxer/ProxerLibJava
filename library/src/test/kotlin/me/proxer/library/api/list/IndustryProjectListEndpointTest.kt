package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.IndustryProject
import me.proxer.library.enums.IndustryType
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class IndustryProjectListEndpointTest : ProxerTest() {

    private val expectedIndustryProject = IndustryProject(
        id = "10198", name = "&Dropout", genres = setOf("Comedy", "SciFi", "Yuri"), fskConstraints = emptySet(),
        medium = Medium.ONESHOT, industryType = IndustryType.PUBLISHER, state = MediaState.FINISHED, ratingSum = 109,
        ratingAmount = 23
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("industry_project_list.json") {
            api.list
                .industryProjectList("333")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedIndustryProject
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("industry_project_list.json") {
            api.list.industryProjectList("543")
                .includeHentai(true)
                .type(IndustryType.RECORD_LABEL)
                .page(3)
                .limit(12)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/industryprojects?id=543&type=record_label&isH=1&p=3&limit=12"
    }

    @Test
    fun testPathNoHentai() {
        val (_, request) = server.runRequest("industry_project_list.json") {
            api.list.industryProjectList("543")
                .includeHentai(false)
                .type(IndustryType.RECORD_LABEL)
                .page(3)
                .limit(12)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/industryprojects?id=543&type=record_label&isH=0&p=3&limit=12"
    }

    @Test
    fun testHentaiNull() {
        val (_, request) = server.runRequest("industry_project_list.json") {
            api.list.industryProjectList("543")
                .includeHentai(true)
                .includeHentai(null)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/industryprojects?id=543"
    }
}
