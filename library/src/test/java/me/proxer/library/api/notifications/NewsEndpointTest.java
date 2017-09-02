package me.proxer.library.api.notifications;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entitiy.notifications.NewsArticle;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class NewsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final List<NewsArticle> result = api.notifications()
                .news()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestArticle());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.notifications().news()
                .page(0)
                .limit(10)
                .markAsRead(false)
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/notifications/news?p=0&limit=10&set_read=false");
    }

    private NewsArticle buildTestArticle() {
        return new NewsArticle("7709", new Date(1488654000L * 1000), "In der diesjährigen 14. Ausgabe "
                + "von Shueishas Weekly Shounen Jump-Magazin soll angekündigt werden, dass der Manga To Love-Ru "
                + "Trouble Darkness eine neue OVA erhält.", "723465714977", "To Love-Ru Trouble "
                + "Darkness – OVA zum zehnjährigen Jubiläum angekündigt", 549, "381362", "101731",
                "SilentGray", 1, "56", "Anime- und Manga-News");
    }
}
