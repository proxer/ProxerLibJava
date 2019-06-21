package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.MediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaListSortCriteria
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.SortType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Desnoo
 */
class MediaListEndpointTest : ProxerTest() {

    private val expectedEntry = MediaListEntry(
        id = "3637", name = "+ A Channel", genres = setOf("Comedy", "School"), medium = Medium.OVA, episodeAmount = 11,
        state = MediaState.FINISHED, ratingSum = 774, ratingAmount = 115,
        languages = setOf(MediaLanguage.ENGLISH_SUB, MediaLanguage.GERMAN_SUB)
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("media_list_entry.json") {
            api.list
                .mediaList()
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("media_list_entry.json") {
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
        }

        request.path shouldEqual """
            /api/v1/list/entrylist?kat=anime&medium=animeseries&isH=true&start=abc&
            sort=rating&sort_type=ASC&p=0&limit=10
        """.trimIndent().replace("\n", "")
    }
}
