package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.Tag
import me.proxer.library.enums.TagSortCriteria
import me.proxer.library.enums.TagSubType
import me.proxer.library.enums.TagType
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TagListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("tags.json")))

        val result = api.list
            .tagList()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
        assertThat(result).element(1).isEqualTo(buildSecondTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("tags.json")))

        api.list.tagList()
            .name("abc")
            .type(TagType.H_TAG)
            .sortCriteria(TagSortCriteria.ID)
            .sortAscending()
            .subType(TagSubType.FUTURE)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/tags?search=abc&type=entry_tag_h&sort=id&sort_type=ASC&subtype=zukunft")
    }

    @Test
    fun testPathDescending() {
        server.enqueue(MockResponse().setBody(fromResource("tags.json")))

        api.list.tagList()
            .name("xyz")
            .type(TagType.GENRE)
            .sortCriteria(TagSortCriteria.SUBTYPE)
            .sortDescending()
            .subType(TagSubType.PEOPLE)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/tags?search=xyz&type=entry_genre&sort=subtype&sort_type=DESC&subtype=menschen")
    }

    private fun buildTestEntry(): Tag {
        return Tag(
            "262", TagType.TAG, "4-Koma", "Seitenaufteilung in vier gleich große Panels.",
            TagSubType.DRAWING, false
        )
    }

    private fun buildSecondTestEntry(): Tag {
        return Tag(
            "175", TagType.GENRE, "Action",
            "Dynamische Szenen, spannende Wettkämpfe " + "und beeindruckende Kampfszenen prägen dieses Genre.",
            TagSubType.MISCELLANEOUS, true
        )
    }
}
