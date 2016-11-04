package com.proxerme.library.connection.manga.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.manga.entity.Chapter;
import com.proxerme.library.parameters.GeneralLanguageParameter;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link ChapterRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class ChapterRequestTest extends RequestTest {

    private static final String URL = "/api/v1/manga/chapter?id=0&episode=1&language=en";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.chapter)));

        Chapter result = connection.executeSynchronized(
                new ChapterRequest("0", 1, GeneralLanguageParameter.ENGLISH)
                        .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestChapter(), result);
    }

    @Test
    public void testUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.chapter)));

        connection.executeSynchronized(
                new ChapterRequest("0", 1, GeneralLanguageParameter.ENGLISH)
                        .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(URL, server.takeRequest().getPath());
    }

    private Chapter generateTestChapter() {
        return new Chapter("1954", "2784", "Chapter 1", "0", "nobody", 1318716000, null, null, "0",
                new String[][]{
                        new String[]{"fairy-001-01.jpg", "1100", "765",},
                        new String[]{"fairy-001-02-03.jpg", "641", "900",},
                });
    }
}
