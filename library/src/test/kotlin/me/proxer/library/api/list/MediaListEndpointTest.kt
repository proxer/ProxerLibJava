package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.MediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaListSortCriteria
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.SortType
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.EnumSet

/**
 * @author Desnoo
 */
class MediaListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        val result = api.list
            .mediaList()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntryEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        api.list.mediaList()
            .category(Category.ANIME)
            .medium(Medium.ANIMESERIES)
            .includeHentai(true)
            .searchStart("abc")
            .sort(MediaListSortCriteria.RATING)
            .sortType(SortType.ASCENDING)
            .limit(10)
            .page(0)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo(
            "/api/v1/list/entrylist?kat=anime&medium=animeseries" +
                "&isH=true&start=abc&sort=rating&sort_type=ASC&p=0&limit=10"
        )
    }

    private fun buildTestEntryEntry(): MediaListEntry {
        return MediaListEntry(
            "3637", "+ A Channel", setOf("Comedy", "School"),
            Medium.OVA, 11, MediaState.FINISHED, 774, 115,
            EnumSet.of(MediaLanguage.ENGLISH_SUB, MediaLanguage.GERMAN_SUB)
        )
    }
}
