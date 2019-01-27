package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.TranslatorGroupProject
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.ProjectState
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.EnumSet

/**
 * @author Ruben Gees
 */
class TranslatorGroupProjectListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group_project_list.json")))

        val result = api.list
            .translatorGroupProjectList("111")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestProject())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group_project_list.json")))

        api.list.translatorGroupProjectList("321")
            .includeHentai(true)
            .projectState(ProjectState.LICENSED)
            .page(3)
            .limit(12)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/translatorgroupprojects?id=321&type=5&isH=1&p=3&limit=12")
    }

    @Test
    fun testPathNoHentai() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group_project_list.json")))

        api.list.translatorGroupProjectList("321")
            .includeHentai(false)
            .projectState(ProjectState.LICENSED)
            .page(3)
            .limit(12)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/translatorgroupprojects?id=321&type=5&isH=0&p=3&limit=12")
    }

    @Test
    fun testHentaiNull() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group_project_list.json")))

        api.list.translatorGroupProjectList("654")
            .includeHentai(true)
            .includeHentai(null)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/list/translatorgroupprojects?id=654")
    }

    private fun buildTestProject(): TranslatorGroupProject {
        return TranslatorGroupProject(
            "15775", "12-Sai: Chiccha na Mune no Tokimeki", setOf("Comedy", "Romance"),
            EnumSet.of(FskConstraint.FSK_0), Medium.ANIMESERIES, ProjectState.ONGOING, MediaState.FINISHED,
            942, 136
        )
    }
}
