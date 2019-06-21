package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.TranslatorGroupCore
import me.proxer.library.enums.Country
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class TranslatorGroupListEndpointTest : ProxerTest() {

    private val expectedGroup = TranslatorGroupCore(
        id = "533", name = "/ak/ Scans", country = Country.ENGLAND,
        image = "http://img1.wikia.nocookie.net/__cb20120723230150/ak-scans/images/5/58/1342952821487.png"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("translator_group_list.json") {
            api.list
                .translatorGroupList()
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedGroup
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("translator_group_list.json") {
            api.list.translatorGroupList()
                .country(Country.JAPAN)
                .search("ab")
                .searchStart("cd")
                .page(1)
                .limit(100)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/list/translatorgroups?start=cd&contains=ab&country=jp&p=1&limit=100"
    }
}
