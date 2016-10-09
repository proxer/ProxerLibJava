package com.proxerme.library.connection.info.request;

import com.proxerme.library.connection.info.entity.Episode;
import com.proxerme.library.connection.info.entity.ListInfo;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.GeneralLanguageParameter;
import com.proxerme.library.parameters.SubDubLanguageParameter;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static junit.framework.Assert.assertEquals;

/**
 * Tests for {@link ListInfoRequest}.
 *
 * @author Ruben Gees
 */
public class ListInfoRequestTest extends RequestTest {

    private static final String URL = "/api/v1/info/listinfo?id=123&p=0";
    private static final String URL_LIMIT = "/api/v1/info/listinfo?id=123&p=0&limit=10";

    @Test
    public void testAnime() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.listinfo_anime)));

        ListInfo result = connection.executeSynchronized(new ListInfoRequest("123", 0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestListInfo(), result);
    }

    @Test
    public void testManga() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.listinfo_manga)));

        ListInfo result = connection.executeSynchronized(new ListInfoRequest("123", 0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestListInfoManga(), result);
    }

    @Test
    public void testDefaultUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.listinfo_manga)));

        connection.executeSynchronized(new ListInfoRequest("123", 0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Assert.assertEquals(URL, server.takeRequest().getPath());
    }

    @Test
    public void testLimitUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.listinfo_manga)));

        connection.executeSynchronized(new ListInfoRequest("123", 0).withLimit(10)
                .withCustomHost(buildHostUrl(server.url(URL_LIMIT))));

        Assert.assertEquals(URL_LIMIT, server.takeRequest().getPath());
    }

    private ListInfo generateTestListInfo() {
        return new ListInfo(1, 12, CategoryParameter.ANIME, new String[]{
                SubDubLanguageParameter.GERMAN_SUB,
                SubDubLanguageParameter.ENGLISH_SUB,
        }, 0, new Episode[]{
                new Episode(1, SubDubLanguageParameter.ENGLISH_SUB, null,
                        "proxer-stream,mp4upload,videoweed,novamov,streamcloud2",
                        "proxer-stream.png,mp4upload.png,videoweed.png,novamov.png,streamcloud.png"),
                new Episode(1, SubDubLanguageParameter.GERMAN_SUB, null,
                        "streamcloud,novamov,mp4upload,proxer-stream,yourupload",
                        "streamcloud.png,novamov.png,mp4upload.png,proxer-stream.png,yourupload.png"),
        });
    }

    private ListInfo generateTestListInfoManga() {
        return new ListInfo(1, 50, CategoryParameter.MANGA, new String[]{
                GeneralLanguageParameter.ENGLISH,
        }, 0, new Episode[]{
                new Episode(1, GeneralLanguageParameter.ENGLISH, "Chapter 1", null, null),
                new Episode(2, GeneralLanguageParameter.ENGLISH, "Chapter 2", null, null),
        });
    }

}