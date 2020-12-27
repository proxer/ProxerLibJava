package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserHistoryEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserHistoryEndpointTest : ProxerTest() {

    private val firstExpectedEntry = UserHistoryEntry(
        id = "457484352",
        entryId = "16209",
        name = "Kono Subarashii Sekai ni Shukufuku wo! 2",
        language = MediaLanguage.ENGLISH_SUB,
        medium = Medium.ANIMESERIES,
        category = Category.ANIME,
        episode = 2
    )

    private val secondExpectedEntry = UserHistoryEntry(
        id = "456582967",
        entryId = "3088",
        name = "Girls of the Wild's",
        language = MediaLanguage.ENGLISH,
        medium = Medium.MANGASERIES,
        category = Category.MANGA,
        episode = 182
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("user_history.json") {
            api.user.history("test", "supersecret")
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo firstExpectedEntry
        result.last() shouldBeEqualTo secondExpectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("user_history.json") {
            api.user.history("test", "supersecret")
                .page(1)
                .limit(12)
                .includeHentai(true)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/user/history?uid=test&username=supersecret&p=1&limit=12&isH=true"
    }
}
