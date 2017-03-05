package com.proxerme.library.api.notifications;

import com.proxerme.library.ApiTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.notifications.NewsArticle;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

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

        System.err.println("Result: " + result);
        System.err.println("Expected: " + buildTestArticle());

        assertThat(result).first().isEqualTo(buildTestArticle());
    }

    private NewsArticle buildTestArticle() {
        return new NewsArticle("6316", 1472382000, "Es wurden kürzlich weitere Synchronsprecher " +
                "vorgestellt, die in der kommenden Anime-Adaption zu Nobunaga no Shinobi eine " +
                "Rolle übernehmen werden. Weiterhin ist nun bekannt, dass die Serie am 04." +
                " Oktober erstausgestrahlt wird.", "942593469984", "Nobunaga no Shinobi \u2013" +
                " weitere Sprecher sowie Erscheinung bekanntgegeben", 4111, "376973", "155334",
                "Minato.", 1, "56", "Anime- und Manga News");
    }
}
