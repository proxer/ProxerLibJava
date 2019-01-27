package me.proxer.library.api.user

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
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserMediaListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_media_list.json")))

        val result = api.user
            .mediaList(null, "rubygee")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_media_list.json")))

        api.user.mediaList("1", "rubygee")
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
            "/api/v1/user/list?uid=1&username=rubygee&kat=anime&p=0&limit=5&search=test&search_start=startTest"
                + "&filter=stateFilter1&sort=stateChangeDateASC&isH=true"
        )
    }

    @Test
    fun testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { api.user.mediaList(null, null) }
    }

    private fun buildTestEntry(): UserMediaListEntry {
        return UserMediaListEntry(
            "16257", "91 Days", 12, Medium.ANIMESERIES, MediaState.FINISHED,
            "18301850", "", UserMediaProgress.WATCHED, 12, 0
        )
    }
}
