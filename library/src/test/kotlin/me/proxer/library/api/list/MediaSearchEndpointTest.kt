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
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.EnumSet

/**
 * @author Desnoo
 */
class MediaSearchEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        val result = api.list
            .mediaSearch()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

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

        assertThat(server.takeRequest().path).isEqualTo(
            "/api/v1/list/entrysearch?name=test&language=en&"
                + "type=all-manga&fsk=fear&sort=clicks&length=300&length-limit=down&tags=3%2B7&notags=5%2B20&"
                + "taggenre=22%2B33&notaggenre=13%2B17&tagratefilter=rate_1&tagspoilerfilter=spoiler_1&hide_finished=1&"
                + "p=3&limit=10"
        )
    }

    @Test
    fun testTagsNull() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        api.list.mediaSearch()
            .tags(setOf("3", "7"))
            .tags(null)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/list/entrysearch")
    }

    @Test
    fun testExcludedTagsNull() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        api.list.mediaSearch()
            .excludedTags(setOf("5", "20"))
            .excludedTags(null)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/list/entrysearch")
    }

    @Test
    fun testGenreTagsNull() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        api.list.mediaSearch()
            .genres(setOf("3", "7"))
            .genres(null)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/list/entrysearch")
    }

    @Test
    fun testExcludedGenreTagsNull() {
        server.enqueue(MockResponse().setBody(fromResource("media_list_entry.json")))

        api.list.mediaSearch()
            .excludedGenres(setOf("5", "20"))
            .excludedGenres(null)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/list/entrysearch")
    }

    private fun buildTestEntry(): MediaListEntry {
        return MediaListEntry(
            "3637", "+ A Channel", setOf("Comedy", "School"),
            Medium.OVA, 11, MediaState.FINISHED, 774, 115,
            EnumSet.of(MediaLanguage.ENGLISH_SUB, MediaLanguage.GERMAN_SUB)
        )
    }
}
