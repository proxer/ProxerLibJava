package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.Tag
import me.proxer.library.enums.TagSortCriteria
import me.proxer.library.enums.TagSubType
import me.proxer.library.enums.TagType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TagListEndpointTest : ProxerTest() {

    private val firstExpectedTag = Tag(
        id = "262",
        type = TagType.TAG,
        name = "4-Koma",
        description = "Seitenaufteilung in vier gleich große Panels.",
        subType = TagSubType.DRAWING,
        isSpoiler = false
    )

    private val secondExpectedTag = Tag(
        id = "175",
        type = TagType.GENRE,
        name = "Action",
        description = "Dynamische Szenen, spannende Wettkämpfe und beeindruckende Kampfszenen prägen dieses Genre.",
        subType = TagSubType.MISCELLANEOUS,
        isSpoiler = true
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("tags.json") {
            api.list
                .tagList()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo firstExpectedTag
        result[1] shouldBeEqualTo secondExpectedTag
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("tags.json") {
            api.list.tagList()
                .name("abc")
                .type(TagType.H_TAG)
                .sortCriteria(TagSortCriteria.ID)
                .sortAscending()
                .subType(TagSubType.FUTURE)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo """
                /api/v1/list/tags?search=abc&type=entry_tag_h&sort=id&sort_type=ASC&subtype=zukunft
        """.trimIndent()
    }

    @Test
    fun testPathDescending() {
        val (_, request) = server.runRequest("tags.json") {
            api.list.tagList()
                .name("xyz")
                .type(TagType.GENRE)
                .sortCriteria(TagSortCriteria.SUBTYPE)
                .sortDescending()
                .subType(TagSubType.PEOPLE)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo """
                /api/v1/list/tags?search=xyz&type=entry_genre&sort=subtype&sort_type=DESC&subtype=menschen
        """.trimIndent()
    }
}
