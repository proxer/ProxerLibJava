package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.entity.notifications.NewsArticle
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class NewsEndpointTest : ProxerTest() {

    private val expectedNews = NewsArticle(
        id = "7709", date = Date(1488654000L * 1000),
        description = """
            |In der diesjährigen 14. Ausgabe von Shueishas Weekly Shounen Jump-Magazin soll angekündigt werden,
            | dass der Manga To Love-Ru Trouble Darkness eine neue OVA erhält.
        """.trimMargin().replace("\n", ""),
        image = "723465714977",
        subject = "To Love-Ru Trouble " + "Darkness – OVA zum zehnjährigen Jubiläum angekündigt", hits = 549,
        threadId = "381362", authorId = "101731", author = "SilentGray", commentAmount = 1, categoryId = "56",
        category = "Anime- und Manga-News"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("news.json") {
            api.notifications
                .news()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedNews
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("news.json") {
            api.notifications.news()
                .page(0)
                .limit(10)
                .markAsRead(false)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/notifications/news?p=0&limit=10&set_read=false"
    }
}
