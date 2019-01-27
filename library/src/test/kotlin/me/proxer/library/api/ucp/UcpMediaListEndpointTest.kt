package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserMediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.UserMediaListFilterType
import me.proxer.library.enums.UserMediaListSortCriteria
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UcpMediaListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_media_list.json")))

        val result = api.ucp
            .mediaList()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_media_list.json")))

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

        assertThat(server.takeRequest().path).isEqualTo(
            "/api/v1/ucp/list?kat=anime&p=0&limit=5&search=test&search_start=startTest"
                    + "&filter=stateFilter1&sort=stateChangeDateASC&isH=true"
        )
    }

    private fun buildTestEntry(): UserMediaListEntry {
        return UserMediaListEntry(
            "16257", "91 Days", 12, Medium.ANIMESERIES, MediaState.FINISHED, "18301850",
            "", UserMediaProgress.WATCHED, 12, 0
        )
    }
}
