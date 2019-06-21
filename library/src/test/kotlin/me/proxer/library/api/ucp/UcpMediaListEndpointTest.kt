package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserMediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.UserMediaListFilterType
import me.proxer.library.enums.UserMediaListSortCriteria
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UcpMediaListEndpointTest : ProxerTest() {

    private val expectedEntry = UserMediaListEntry(
        id = "16257", name = "91 Days", episodeAmount = 12, medium = Medium.ANIMESERIES, state = MediaState.FINISHED,
        commentId = "18301850", commentContent = "", mediaProgress = UserMediaProgress.WATCHED, episode = 12,
        rating = 0
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("user_media_list.json") {
            api.ucp
                .mediaList()
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("user_media_list.json") {
            api.ucp.mediaList()
                .category(Category.ANIME)
                .page(0)
                .limit(5)
                .search("test")
                .searchStart("startTest")
                .filter(UserMediaListFilterType.WATCHING)
                .sort(UserMediaListSortCriteria.STATE_CHANGE_DATE_ASCENDING)
                .includeHentai(true)
                .build()
                .execute()
        }

        request.path shouldEqual """
            /api/v1/ucp/list?kat=anime&p=0&limit=5&search=test&search_start=startTest&filter=stateFilter1
            &sort=stateChangeDateASC&isH=true
        """.trimIndent().replace("\n", "")
    }
}
