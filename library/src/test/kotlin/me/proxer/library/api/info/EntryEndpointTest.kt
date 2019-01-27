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
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.IndustryType
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.Season
import me.proxer.library.enums.SynonymType
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.EnumSet
import java.util.Locale

/**
 * @author Ruben Gees
 */
class EntryEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("entry.json")))

        val result = api.info
            .entry("1")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("entry.json")))

        api.info.entry("1")
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/fullentry?id=1")
    }

    private fun buildTestEntry(): Entry {
        return Entry(
            "6174", "LuCu LuCu", EnumSet.noneOf(FskConstraint::class.java),
            "Humans are a despicable lot, committing sin after sin, filling the endless boundaries " +
                "of the underworld with tortured souls. Now, it would seem, Hell isn't so endless after all, " +
                "and it has become dangerously close to filling, and then overflowing into the human realm. " +
                "Princess Lucuha sees this imminent disaster and has a plan: save Hell by making humans " +
                "decent again. Of course, the angels can't simply allow demons to roam freely on Earth, " +
                "and they do their best to stop Lucu and her dastardly plans.",
            Medium.MANGASERIES, 90, MediaState.FINISHED, 7, 1, 134,
            Category.MANGA, License.NOT_LICENSED, AdaptionInfo("2793", "KissXsis", Medium.MANGASERIES), false,
            listOf(
                Synonym("12322", "6174", SynonymType.ORIGINAL, "LuCu LuCu"),
                Synonym("12323", "6174", SynonymType.JAPANESE, "るくるく"),
                Synonym("44662", "6174", SynonymType.ORIGINAL_ALTERNATIVE, "Lucu Lucu"),
                Synonym("44663", "6174", SynonymType.ORIGINAL_ALTERNATIVE, "LuCuLuCu")
            ),
            setOf(MediaLanguage.ENGLISH),
            listOf(
                EntrySeasonInfo("1061", 2002, Season.UNSPECIFIED),
                EntrySeasonInfo("15776", 2009, Season.UNSPECIFIED)
            ),
            listOf(
                EntryTranslatorGroup("215", "SnoopyCool", Country.ENGLAND),
                EntryTranslatorGroup("294", "FoOlRulez", Country.ENGLAND)
            ),
            listOf(IndustryCore("19", "Kodansha", IndustryType.PUBLISHER, Country.JAPAN)),
            listOf(
                InfoTag(
                    "2027", "93",
                    SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2016-06-18 14:14:22"),
                    false, false, "Dämonen", "In diesem Werk kommen Dämonen vor."
                ),
                InfoTag(
                    "2028", "299",
                    SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2016-06-18 14:14:30"),
                    false, false, "Slapstick", "Situationskomik, kommt ohne Worte aus."
                )
            ),
            listOf(
                InfoGenre(
                    "120694", "175",
                    SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2018-03-07 23:17:44"),
                    "Action",
                    "Dynamische Szenen, spannende Wettkämpfe und beeindruckende " +
                        "Kampfszenen prägen dieses Genre."
                ),
                InfoGenre(
                    "120695", "174",
                    SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMANY).parse("2018-03-07 23:17:44"),
                    "Adventure",
                    "Es handelt sich meist um eine Geschichte über eine Reise oder Suche. " +
                        "Kurzum ein Abenteuer, das es zu bestehen gilt."
                )
            )
        )
    }
}
