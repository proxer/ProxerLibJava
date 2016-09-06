package com.proxerme.library.connection.ucp.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.ucp.entitiy.HistoryEntry;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.proxerme.library.parameters.SubDubLanguageParameter;
import com.proxerme.library.test.R;
import com.proxerme.library.util.RequestTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link HistoryRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class HistoryRequestTest extends RequestTest {

    private static final String URL = "/api/v1/ucp/history?p=0";
    private static final String LIMIT_URL = "/api/v1/ucp/history?p=0&limit=10";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.history)));

        HistoryEntry[] result = connection.executeSynchronized(new HistoryRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestEntry(), result[0]);
    }

    @Test
    public void testDefaultUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.history)));

        connection.executeSynchronized(new HistoryRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(URL, server.takeRequest().getPath());
    }

    @Test
    public void testLimitUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.history)));

        connection.executeSynchronized(new HistoryRequest(0).withLimit(10)
                .withCustomHost(buildHostUrl(server.url(LIMIT_URL))));

        assertEquals(LIMIT_URL, server.takeRequest().getPath());
    }

    private HistoryEntry generateTestEntry() {
        return new HistoryEntry("1969", "Nurarihyon no Mago: Sennen Makyou",
                SubDubLanguageParameter.GERMAN_SUB, MediumParameter.ANIMESERIES,
                CategoryParameter.ANIME, 7, "2016-09-06 01:12:59");
    }
}
