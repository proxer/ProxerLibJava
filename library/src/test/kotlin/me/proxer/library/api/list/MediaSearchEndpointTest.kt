package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.MediaListEntry
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.Language
import me.proxer.library.enums.LengthBound
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaSearchSortCriteria
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.MediaType
import me.proxer.library.enums.Medium
import me.proxer.library.enums.TagRateFilter
import me.proxer.library.enums.TagSpoilerFilter
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.EnumSet

/**
 * @author Desnoo
 */
class MediaSearchEndpointTest : ProxerTest() {

    private val expectedEntry = MediaListEntry(
        id = "3637", name = "+ A Channel", genres = setOf("Comedy", "School"), medium = Medium.OVA, episodeAmount = 11,
        state = MediaState.FINISHED, ratingSum = 774, ratingAmount = 115,
        languages = setOf(MediaLanguage.ENGLISH_SUB, MediaLanguage.GERMAN_SUB)
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("media_list_entry.json") {
            api.list
                .mediaSearch()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("media_list_entry.json") {
            api.list.mediaSearch()
                .name("test")
                .limit(10)
                .page(3)
                .tags(setOf("3", "7"))
                .excludedTags(setOf("5", "20"))
                .genres(setOf("22", "33"))
                .excludedGenres(setOf("13", "17"))
                .fskConstraints(EnumSet.of(FskConstraint.FEAR))
                .language(Language.ENGLISH)
                .length(300)
                .lengthBound(LengthBound.LOWER)
                .tagRateFilter(TagRateFilter.RATED_ONLY)
                .tagSpoilerFilter(TagSpoilerFilter.ONLY_SPOILERS)
                .type(MediaType.ALL_MANGA)
                .sort(MediaSearchSortCriteria.CLICKS)
                .hideFinished(true)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo """
                /api/v1/list/entrysearch?name=test&language=en&type=all-manga&fsk=fear&sort=clicks&length=300
                &length-limit=down&tags=3%2B7&notags=5%2B20&taggenre=22%2B33&notaggenre=13%2B17&tagratefilter=rate_1
                &tagspoilerfilter=spoiler_1&hide_finished=1&p=3&limit=10
        """.trimIndent().replace("\n", "")
    }

    @Test
    fun testTagsNull() {
        val (_, request) = server.runRequest("media_list_entry.json") {
            api.list.mediaSearch()
                .tags(setOf("3", "7"))
                .tags(null)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/list/entrysearch"
    }

    @Test
    fun testExcludedTagsNull() {
        val (_, request) = server.runRequest("media_list_entry.json") {
            api.list.mediaSearch()
                .excludedTags(setOf("5", "20"))
                .excludedTags(null)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/list/entrysearch"
    }

    @Test
    fun testGenreTagsNull() {
        val (_, request) = server.runRequest("media_list_entry.json") {
            api.list.mediaSearch()
                .genres(setOf("3", "7"))
                .genres(null)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/list/entrysearch"
    }

    @Test
    fun testExcludedGenreTagsNull() {
        val (_, request) = server.runRequest("media_list_entry.json") {
            api.list.mediaSearch()
                .excludedGenres(setOf("5", "20"))
                .excludedGenres(null)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/list/entrysearch"
    }
}
