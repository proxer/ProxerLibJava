package com.proxerme.library.connection.notifications.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link NewsRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class NewsRequestTest extends RequestTest {

    private static final String URL = "/v1/notifications/news?p=0";
    private static final String URL_LIMIT = "/v1/notifications/news?p=0&limit=7";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        News[] result = connection.executeSynchronized(new NewsRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestNews(), result[0]);
    }

    @Test
    public void testLimit() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_limit)));

        News[] result = connection.executeSynchronized(new NewsRequest(0).withLimit(7)
                .withCustomHost(buildHostUrl(server.url(URL_LIMIT))));

        assertEquals(7, result.length);
    }

    private News generateTestNews() {
        return new News("6316", 1472382000, "Es wurden kürzlich weitere Synchronsprecher " +
                "vorgestellt, die in der kommenden Anime-Adaption zu Nobunaga no Shinobi eine " +
                "Rolle übernehmen werden. Weiterhin ist nun bekannt, dass die Serie am 04." +
                " Oktober erstausgestrahlt wird.", "942593469984", "Nobunaga no Shinobi \u2013" +
                " weitere Sprecher sowie Erscheinung bekanntgegeben", 4111, "376973", "155334",
                "Minato.", 1, "56", "Anime- und Manga News");
    }
}
