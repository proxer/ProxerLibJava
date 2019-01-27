package me.proxer.library.api.notifications

import me.proxer.library.ProxerTest
import me.proxer.library.entity.notifications.NewsArticle
import me.proxer.library.fromResource
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author Ruben Gees
 */
class NewsEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        val result = api.notifications
            .news()
            .build()
            .execute()

        assertThat(result).first().isEqualTo(buildTestArticle())
    }

    @Test
    fun testPath() {
        server.enqueue(MockResponse().setBody(fromResource("news.json")))

        api.notifications.news()
            .page(0)
            .limit(10)
            .markAsRead(false)
            .build()
            .execute()

        assertThat(server.takeRequest().path).isEqualTo("/api/v1/notifications/news?p=0&limit=10&set_read=false")
    }

    private fun buildTestArticle(): NewsArticle {
        return NewsArticle(
            "7709", Date(1488654000L * 1000),
            "In der diesjährigen 14. Ausgabe von Shueishas Weekly Shounen Jump-Magazin soll angekündigt "
                + "werden, dass der Manga To Love-Ru Trouble Darkness eine neue OVA erhält.",
            "723465714977", "To Love-Ru Trouble " + "Darkness – OVA zum zehnjährigen Jubiläum angekündigt",
            549, "381362", "101731", "SilentGray", 1, "56", "Anime- und Manga-News"
        )
    }
}
