package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.TranslatorGroupCore
import me.proxer.library.enums.Country
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TranslatorGroupListEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group_list.json")))

        val result = api.list
            .translatorGroupList()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestGroup())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("translator_group_list.json")))

        api.list.translatorGroupList()
            .country(Country.JAPAN)
            .search("ab")
            .searchStart("cd")
            .page(1)
            .limit(100)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/list/translatorgroups?start=cd&contains=ab&country=jp&p=1&limit=100")
    }

    private fun buildTestGroup(): TranslatorGroupCore {
        return TranslatorGroupCore(
            "533", "/ak/ Scans", Country.ENGLAND,
            "http://img1.wikia.nocookie.net/__cb20120723230150/ak-scans/images/5/58/1342952821487.png"
        )
    }
}
