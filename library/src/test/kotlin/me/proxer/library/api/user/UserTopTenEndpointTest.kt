package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.TopTenEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserTopTenEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("user_top_ten_anime.json")))

        val result = api.user
            .topTen(null, "rubygee")
            .category(Category.ANIME)
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestEntry())
    }

    @Test
    fun testManga() {
        server.enqueue(MockResponse().setBody(fromResource("user_top_ten_manga.json")))

        val result = api.user
            .topTen(null, "rubygee")
            .category(Category.MANGA)
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildMangaTestEntry())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("user_top_ten_manga.json")))

        api.user.topTen("123", "rubygee")
            .category(Category.ANIME)
            .includeHentai(true)
            .build()
            .execute()

        Assertions.assertThat(server.takeRequest().path)
            .isEqualTo("/api/v1/user/topten?uid=123&username=rubygee&kat=anime&isH=true")
    }

    @Test
    fun testUserIdAndUsernameNull() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { api.user.topTen(null, null) }
    }

    private fun buildTestEntry(): TopTenEntry {
        return TopTenEntry("13633", "Boku dake ga Inai Machi", Category.ANIME, Medium.ANIMESERIES)
    }

    private fun buildMangaTestEntry(): TopTenEntry {
        return TopTenEntry("6015", "Citrus (Saburouta)", Category.MANGA, Medium.MANGASERIES)
    }
}
