package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.AdaptionInfo
import me.proxer.library.entity.info.Entry
import me.proxer.library.entity.info.EntrySeasonInfo
import me.proxer.library.entity.info.EntryTranslatorGroup
import me.proxer.library.entity.info.InfoGenre
import me.proxer.library.entity.info.InfoTag
import me.proxer.library.entity.info.Synonym
import me.proxer.library.entity.list.IndustryCore
import me.proxer.library.enums.Category
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.Season
import me.proxer.library.enums.SynonymType
import me.proxer.library.runRequest
import me.proxer.library.toProxerDateTime
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class EntryEndpointTest : ProxerTest() {

    private val expectedEntry = Entry(
        id = "6174", name = "LuCu LuCu", fskConstraints = setOf(),
        description = """
            |Humans are a despicable lot, committing sin after sin, filling the endless boundaries of the
            | underworld with tortured souls. Now, it would seem, Hell isn't so endless after all, and it has become
            | dangerously close to filling, and then overflowing into the human realm. Princess Lucuha sees this
            | imminent disaster and has a plan: save Hell by making humans decent again. Of course, the angels can't
            | simply allow demons to roam freely on Earth, and they do their best to stop Lucu and her
            | dastardly plans.
        """.trimMargin().replace("\n", ""),
        medium = Medium.MANGASERIES, episodeAmount = 90, state = MediaState.FINISHED, ratingSum = 7, ratingAmount = 1,
        clicks = 134, category = Category.MANGA, license = License.NOT_LICENSED,
        adaptionInfo = AdaptionInfo(id = "2793", name = "KissXsis", medium = Medium.MANGASERIES),
        isAgeRestricted = false,
        synonyms = listOf(
            Synonym(id = "12322", entryId = "6174", type = SynonymType.ORIGINAL, name = "LuCu LuCu"),
            Synonym(id = "12323", entryId = "6174", type = SynonymType.JAPANESE, name = "るくるく"),
            Synonym(id = "44662", entryId = "6174", type = SynonymType.ORIGINAL_ALTERNATIVE, name = "Lucu Lucu"),
            Synonym(id = "44663", entryId = "6174", type = SynonymType.ORIGINAL_ALTERNATIVE, name = "LuCuLuCu")
        ),
        languages = setOf(MediaLanguage.ENGLISH),
        seasons = listOf(
            EntrySeasonInfo(id = "1061", year = 2002, season = Season.UNSPECIFIED),
            EntrySeasonInfo(id = "15776", year = 2009, season = Season.UNSPECIFIED)
        ),
        translatorGroups = listOf(
            EntryTranslatorGroup(id = "215", name = "SnoopyCool", country = Country.ENGLAND),
            EntryTranslatorGroup(id = "294", name = "FoOlRulez", country = Country.ENGLAND)
        ),
        industries = listOf(
            IndustryCore(id = "19", name = "Kodansha", type = IndustryType.PUBLISHER, country = Country.JAPAN)
        ),
        tags = listOf(
            InfoTag(
                id = "2027",
                entryTagId = "93",
                date = "2016-06-18 14:14:22".toProxerDateTime(),
                isRated = false,
                isSpoiler = false,
                name = "Dämonen",
                description = "In diesem Werk kommen Dämonen vor."
            ),
            InfoTag(
                id = "2028",
                entryTagId = "299",
                date = "2016-06-18 14:14:30".toProxerDateTime(),
                isRated = false,
                isSpoiler = false,
                name = "Slapstick",
                description = "Situationskomik, kommt ohne Worte aus."
            )
        ),
        genres = listOf(
            InfoGenre(
                id = "120694",
                entryTagId = "175",
                date = "2018-03-07 23:17:44".toProxerDateTime(),
                name = "Action",
                description = """
                    Dynamische Szenen, spannende Wettkämpfe und beeindruckende Kampfszenen prägen dieses Genre.
                """.trimIndent().replace("\n", "")
            ),
            InfoGenre(
                id = "120695",
                entryTagId = "174",
                date = "2018-03-07 23:17:44".toProxerDateTime(),
                name = "Adventure",
                description = """
                    |Es handelt sich meist um eine Geschichte über eine Reise oder Suche.
                    | Kurzum ein Abenteuer, das es zu bestehen gilt.
                """.trimMargin().replace("\n", "")
            )
        )
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("entry.json") {
            api.info
                .entry("1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("entry.json") {
            api.info.entry("1")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/fullentry?id=1"
    }
}
