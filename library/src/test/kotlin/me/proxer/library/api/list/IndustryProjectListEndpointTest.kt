package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.IndustryProject
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.IndustryType
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.EnumSet

/**
 * @author Ruben Gees
 */
class IndustryProjectListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("industry_project_list.json")))

        val result = api.list
            .industryProjectList("333")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestProject())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("industry_project_list.json")))

        api.list.industryProjectList("543")
            .includeHentai(true)
            .type(IndustryType.RECORD_LABEL)
            .page(3)
            .limit(12)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/industryprojects?id=543&type=record_label&isH=1&p=3&limit=12")
    }

    @Test
    fun testPathNoHentai() {
        server.enqueue(MockResponse().setBody(fromResource("industry_project_list.json")))

        api.list.industryProjectList("543")
            .includeHentai(false)
            .type(IndustryType.RECORD_LABEL)
            .page(3)
            .limit(12)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/industryprojects?id=543&type=record_label&isH=0&p=3&limit=12")
    }

    @Test
    fun testHentaiNull() {
        server.enqueue(MockResponse().setBody(fromResource("industry_project_list.json")))

        api.list.industryProjectList("543")
            .includeHentai(true)
            .includeHentai(null)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/list/industryprojects?id=543")
    }

    private fun buildTestProject(): IndustryProject {
        return IndustryProject(
            "10198", "&Dropout", setOf("Comedy", "SciFi", "Yuri"),
            EnumSet.noneOf(FskConstraint::class.java), Medium.ONESHOT, IndustryType.PUBLISHER, MediaState.FINISHED,
            109, 23
        )
    }
}
