package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.UserHistoryEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserHistoryEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_history.json")))

        val result = api.user.history("test", "supersecret")
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildFirstTestEntry())
        assertThat(result).last().isEqualTo(buildLastTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_history.json")))

        api.user.history("test", "supersecret")
            .page(1)
            .limit(12)
            .includeHentai(true)
            .build()
            .execute()

        assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/user/history?uid=test&username=supersecret&p=1&limit=12&isH=true")
    }

    private fun buildFirstTestEntry(): UserHistoryEntry {
        return UserHistoryEntry(
            "457484352", "16209", "Kono Subarashii Sekai ni Shukufuku wo! 2",
            MediaLanguage.ENGLISH_SUB, Medium.ANIMESERIES, Category.ANIME, 2
        )
    }

    private fun buildLastTestEntry(): UserHistoryEntry {
        return UserHistoryEntry(
            "456582967", "3088", "Girls of the Wild's",
            MediaLanguage.ENGLISH, Medium.MANGASERIES, Category.MANGA, 182
        )
    }
}
