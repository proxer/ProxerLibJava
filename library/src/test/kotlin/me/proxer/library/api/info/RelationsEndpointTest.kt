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
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.EnumSet

/**
 * @author Ruben Gees
 */
class RelationsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("relations.json")))

        val result = api.info
            .relations("1")
            .build()
            .execute()

        assertThat(result).isEqualTo(buildTestRelations())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("relations.json")))

        api.info.relations("115")
            .includeHentai(true)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/info/relations?id=115&isH=true")
    }

    private fun buildTestRelations(): List<Relation> {
        return listOf(
            Relation(
                "18598", "Sword Art Online dj - Asuna Kouryakubon",
                setOf("Adult"), EnumSet.of(FskConstraint.FSK_18, FskConstraint.SEX), "N/A",
                Medium.DOUJIN, 1, MediaState.FINISHED, 14, 3, 122,
                Category.MANGA, License.UNKNOWN, EnumSet.of(MediaLanguage.ENGLISH), null, null
            ),
            Relation(
                "4167", "Sword Art Online",
                setOf("Adventure", "Action", "Comedy", "Drama", "Fantasy", "SciFi"),
                EnumSet.of(FskConstraint.FSK_12, FskConstraint.BAD_LANGUAGE, FskConstraint.VIOLENCE),
                "Kazuto Kirigaya testet als einer der ersten einen neuen Hightech-Helm, welcher "
                        + "die Psyche des Nutzers komplett in die Welt des MMORPGs „Sword Art Online“ "
                        + "transferiert. Als Tester der Beta-Version besitzt er bereits einiges an Erfahrung "
                        + "und kämpfte sich mehr als erfolgreich als „Kirito“ durch die ersten Ebenen der "
                        + "Fantasy-Welt. Doch schon kurz nach der Eröffnung SAOs merken die Spieler, dass "
                        + "etwas nicht stimmt: Im Menü gibt es keinen Logout-Button. Hinter dem Grund der "
                        + "allgemeinen aufkommenden Panik scheint der Administrator des Spiels zu stecken "
                        + "und die einzige Möglichkeit wieder in die reale Welt zurückzukehren besteht "
                        + "darin, SAO erfolgreich abzuschließen. Doch das ist leichter gesagt als getan, "
                        + "denn der Tod in der Fantasy-Welt bedeutet auch den richtigen Tod in der "
                        + "richtigen Welt.",
                Medium.ANIMESERIES, 25, MediaState.FINISHED, 158782, 17935,
                5968, Category.ANIME, License.LICENSED,
                EnumSet.of(MediaLanguage.ENGLISH_SUB, MediaLanguage.ENGLISH_DUB, MediaLanguage.GERMAN_DUB),
                2012, Season.SUMMER
            )
        )
    }
}
