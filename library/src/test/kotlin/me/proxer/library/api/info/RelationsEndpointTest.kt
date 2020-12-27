package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.Relation
import me.proxer.library.enums.Category
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.License
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.MediaState
import me.proxer.library.enums.Medium
import me.proxer.library.enums.Season
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class RelationsEndpointTest : ProxerTest() {

    private val expectedRelations = listOf(
        Relation(
            id = "18598", name = "Sword Art Online dj - Asuna Kouryakubon", genres = setOf("Adult"),
            fskConstraints = setOf(FskConstraint.FSK_18, FskConstraint.SEX), description = "N/A",
            medium = Medium.DOUJIN, episodeAmount = 1, state = MediaState.FINISHED, ratingSum = 14, ratingAmount = 3,
            clicks = 122, category = Category.MANGA, license = License.UNKNOWN,
            languages = setOf(MediaLanguage.ENGLISH), year = null, season = null
        ),
        Relation(
            id = "4167", name = "Sword Art Online",
            genres = setOf("Adventure", "Action", "Comedy", "Drama", "Fantasy", "SciFi"),
            fskConstraints = setOf(FskConstraint.FSK_12, FskConstraint.BAD_LANGUAGE, FskConstraint.VIOLENCE),
            description =
            """
                |Kazuto Kirigaya testet als einer der ersten einen neuen Hightech-Helm, welcher die Psyche
                | des Nutzers komplett in die Welt des MMORPGs „Sword Art Online“ transferiert. Als Tester der
                | Beta-Version besitzt er bereits einiges an Erfahrung und kämpfte sich mehr als erfolgreich als
                | „Kirito“ durch die ersten Ebenen der Fantasy-Welt. Doch schon kurz nach der Eröffnung SAOs merken
                | die Spieler, dass etwas nicht stimmt: Im Menü gibt es keinen Logout-Button. Hinter dem Grund der
                | allgemeinen aufkommenden Panik scheint der Administrator des Spiels zu stecken und die einzige
                | Möglichkeit wieder in die reale Welt zurückzukehren besteht darin, SAO erfolgreich abzuschließen.
                | Doch das ist leichter gesagt als getan, denn der Tod in der Fantasy-Welt bedeutet auch den richtigen
                | Tod in der richtigen Welt.
            """.trimMargin().replace(
                "\n",
                ""
            ),
            medium = Medium.ANIMESERIES, episodeAmount = 25, state = MediaState.FINISHED, ratingSum = 158782,
            ratingAmount = 17935, clicks = 5968, category = Category.ANIME, license = License.LICENSED,
            languages = setOf(MediaLanguage.GERMAN_DUB, MediaLanguage.ENGLISH_SUB, MediaLanguage.ENGLISH_DUB),
            year = 2012, season = Season.SUMMER
        )
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("relations.json") {
            api.info
                .relations("1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedRelations
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("relations.json") {
            api.info.relations("115")
                .includeHentai(true)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/relations?id=115&isH=true"
    }
}
