package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.TranslatorGroup
import me.proxer.library.enums.Country
import me.proxer.library.runRequest
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TranslatorGroupEndpointTest : ProxerTest() {

    private val expectedTranslatorGroup = TranslatorGroup(
        id = "11", name = "Gruppe Kampfkuchen", country = Country.GERMANY, image = "http://i.imgur.com/hBoT4Ax.png",
        link = "http://kampfkuchen.de".toHttpUrlOrNull(),
        description = "Gruppe Kampfkuchen, die Gruppe für qualitativ hochwertige Lolisubs! Qualität vor Quantität!",
        clicks = 5087, projectAmount = 23
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("translator_group.json") {
            api.info
                .translatorGroup("4")
                .build()
                .execute()
        }

        result shouldEqual expectedTranslatorGroup
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("translator_group.json") {
            api.info.translatorGroup("12")
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/info/translatorgroup?id=12"
    }
}
