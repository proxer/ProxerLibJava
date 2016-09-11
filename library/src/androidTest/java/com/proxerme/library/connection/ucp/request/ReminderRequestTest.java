package com.proxerme.library.connection.ucp.request;

import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.ucp.entitiy.Reminder;
import com.proxerme.library.parameters.CategoryParameter;
import com.proxerme.library.parameters.MediumParameter;
import com.proxerme.library.parameters.StateParameter;
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
 * Tests for {@link ReminderRequest}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class ReminderRequestTest extends RequestTest {

    private static final String URL = "/api/v1/ucp/reminder?p=0";
    private static final String LIMIT_URL = "/api/v1/ucp/reminder?p=0&limit=7";
    private static final String CATEGORY_URL = "/api/v1/ucp/reminder?p=0&kat=anime";

    @Test
    public void testDefault() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.reminder)));

        Reminder[] result = connection.executeSynchronized(new ReminderRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(generateTestEntry(), result[0]);
    }

    @Test
    public void testDefaultUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.reminder)));

        connection.executeSynchronized(new ReminderRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(URL, server.takeRequest().getPath());
    }

    @Test
    public void testLimitUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.reminder)));

        connection.executeSynchronized(new ReminderRequest(0).withLimit(7)
                .withCustomHost(buildHostUrl(server.url(LIMIT_URL))));

        assertEquals(LIMIT_URL, server.takeRequest().getPath());
    }

    @Test
    public void testCategoryUrl() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.reminder)));

        connection.executeSynchronized(new ReminderRequest(0).withCategory(CategoryParameter.ANIME)
                .withCustomHost(buildHostUrl(server.url(CATEGORY_URL))));

        assertEquals(CATEGORY_URL, server.takeRequest().getPath());
    }

    private Reminder generateTestEntry() {
        return new Reminder("36614132", "13975", CategoryParameter.ANIME,
                "Re:Zero kara Hajimeru Isekai Seikatsu", 1, SubDubLanguageParameter.ENGLISH_SUB,
                MediumParameter.ANIMESERIES, StateParameter.AIRING);
    }
}
