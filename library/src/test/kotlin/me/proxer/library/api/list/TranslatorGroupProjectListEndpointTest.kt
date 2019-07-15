package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.TranslatorGroupProject
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.ProjectState
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TranslatorGroupProjectListEndpointTest : ProxerTest() {

    private val expectedTranslatorGroup = TranslatorGroupProject(
        id = "15775", name = "12-Sai: Chiccha na Mune no Tokimeki", genres = setOf("Comedy", "Romance"),
        fskConstraints = setOf(FskConstraint.FSK_0), medium = Medium.ANIMESERIES, projectState = ProjectState.ONGOING,
        state = MediaState.FINISHED, ratingSum = 942, ratingAmount = 136
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("translator_group_project_list.json") {
            api.list
                .translatorGroupProjectList("111")
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedTranslatorGroup
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("translator_group_project_list.json") {
            api.list.translatorGroupProjectList("321")
                .includeHentai(true)
                .projectState(ProjectState.LICENSED)
                .page(3)
                .limit(12)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/translatorgroupprojects?id=321&type=5&isH=true&p=3&limit=12"
    }

    @Test
    fun testPathNoHentai() {
        val (_, request) = server.runRequest("translator_group_project_list.json") {
            api.list.translatorGroupProjectList("321")
                .includeHentai(false)
                .projectState(ProjectState.LICENSED)
                .page(3)
                .limit(12)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/translatorgroupprojects?id=321&type=5&isH=false&p=3&limit=12"
    }

    @Test
    fun testHentaiNull() {
        val (_, request) = server.runRequest("translator_group_project_list.json") {
            api.list.translatorGroupProjectList("654")
                .includeHentai(true)
                .includeHentai(null)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/translatorgroupprojects?id=654"
    }
}
