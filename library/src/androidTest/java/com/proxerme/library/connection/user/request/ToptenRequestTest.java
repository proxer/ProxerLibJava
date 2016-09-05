package com.proxerme.library.connection.user.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;
import com.proxerme.library.util.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link ToptenRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class ToptenRequestTest extends RequestTest {

    private static final String USER_ID = "123";
    private static final String USERNAME = "321";
    private static final String ANIME_URL = "/api/v1/user/topten?uid=123&username=321";
    private static final String MANGA_URL = "/api/v1/user/topten?uid=123&username=321&kat=manga";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(TestUtils.loadResponse(R.raw.topten_anime)));

        ToptenEntry[] result = connection.executeSynchronized(new ToptenRequest(USER_ID, USERNAME)
                .withCustomHost(buildHostUrl(server.url(ANIME_URL))));

        assertEquals(buildTestEntry(), result[0]);
    }

    @Test
    public void testDefaultUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(TestUtils.loadResponse(R.raw.topten_anime)));

        connection.executeSynchronized(new ToptenRequest(USER_ID, USERNAME)
                .withCustomHost(buildHostUrl(server.url(ANIME_URL))));

        assertEquals(ANIME_URL, server.takeRequest().getPath());
    }

    @Test
    public void testDefaultCount() throws Exception {
        server.enqueue(new MockResponse().setBody(TestUtils.loadResponse(R.raw.topten_anime)));

        ToptenEntry[] result = connection.executeSynchronized(new ToptenRequest(USER_ID, USERNAME)
                .withCustomHost(buildHostUrl(server.url(ANIME_URL))));

        assertEquals(10, result.length);
    }

    @Test
    public void testManga() throws Exception {
        server.enqueue(new MockResponse().setBody(TestUtils.loadResponse(R.raw.topten_manga)));

        ToptenEntry[] result = connection.executeSynchronized(new ToptenRequest(USER_ID, USERNAME,
                CategoryParameter.MANGA).withCustomHost(buildHostUrl(server.url(MANGA_URL))));

        assertEquals(buildMangaTestEntry(), result[0]);
    }

    @Test
    public void testMangaCount() throws Exception {
        server.enqueue(new MockResponse().setBody(TestUtils.loadResponse(R.raw.topten_manga)));

        ToptenEntry[] result = connection.executeSynchronized(new ToptenRequest(USER_ID, USERNAME,
                CategoryParameter.MANGA).withCustomHost(buildHostUrl(server.url(MANGA_URL))));

        assertEquals(3, result.length);
    }

    @Test
    public void testMangaUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(TestUtils.loadResponse(R.raw.topten_manga)));

        connection.executeSynchronized(new ToptenRequest(USER_ID, USERNAME,
                CategoryParameter.MANGA).withCustomHost(buildHostUrl(server.url(MANGA_URL))));

        assertEquals(MANGA_URL, server.takeRequest().getPath());
    }

    private ToptenEntry buildTestEntry() {
        return new ToptenEntry("13633", "Boku dake ga Inai Machi", "anime", "animeseries");
    }

    private ToptenEntry buildMangaTestEntry() {
        return new ToptenEntry("6015", "Citrus (Saburouta)", "manga", "mangaseries");
    }
}
