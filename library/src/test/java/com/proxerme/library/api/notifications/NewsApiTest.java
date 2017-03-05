package com.proxerme.library.api.notifications;

import com.proxerme.library.ApiTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.notifications.NewsArticle;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class NewsApiTest extends ApiTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final List<NewsArticle> result = api.notifications()
                .news()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestArticle());
    }

    private NewsArticle buildTestArticle() {
        return new NewsArticle("7709", 1488654000, "In der diesjährigen 14. Ausgabe von " +
                "Shueishas Weekly Shounen Jump-Magazin soll angekündigt werden, dass der Manga To Love-Ru Trouble " +
                "Darkness eine neue OVA erhält.", "723465714977", "To Love-Ru Trouble Darkness – " +
                "OVA zum zehnjährigen Jubiläum angekündigt", 549, "381362", "101731",
                "SilentGray", 1, "56", "Anime- und Manga-News");
    }
}
