package me.proxer.library.api.user

import me.proxer.library.ProxerTest
import me.proxer.library.entity.user.TopTenEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.Medium
import me.proxer.library.runRequest
import org.amshove.kluent.invoking
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class UserTopTenEndpointTest : ProxerTest() {

    private val expectedEntryAnime = TopTenEntry(
        id = "13633", name = "Boku dake ga Inai Machi", category = Category.ANIME, medium = Medium.ANIMESERIES
    )

    private val expectedEntryManga = TopTenEntry(
        id = "6015", name = "Citrus (Saburouta)", category = Category.MANGA, medium = Medium.MANGASERIES
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("user_top_ten_anime.json") {
            api.user
                .topTen(null, "rubygee")
                .category(Category.ANIME)
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedEntryAnime
    }

    @Test
    fun testManga() {
        val (result, _) = server.runRequest("user_top_ten_manga.json") {
            api.user
                .topTen(null, "rubygee")
                .category(Category.MANGA)
                .build()
                .safeExecute()
        }

        result.first() shouldEqual expectedEntryManga
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("user_top_ten_manga.json") {
            api.user.topTen("123", "rubygee")
                .category(Category.ANIME)
                .includeHentai(true)
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/user/topten?uid=123&username=rubygee&kat=anime&isH=true"
    }

    @Test
    fun testUserIdAndUsernameNull() {
        invoking { api.user.topTen(null, null) } shouldThrow IllegalArgumentException::class
    }
}
