package com.proxerme.library.connection;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.connection.notifications.request.NewsRequest;
import com.proxerme.library.test.R;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;

/**
 * Tests for {@link ProxerConnection}.
 *
 * @author Ruben Gees
 */
@RunWith(AndroidJUnit4.class)
public class ProxerConnectionTest {

    private static final String URL = "/v1/notifications/news?p=0";
    private static final String ERROR_EXPECTED_EXCEPTION = "Expected Exception was not thrown.";
    private static final String ERROR_EXCEPTION = "An exception was thrown.";

    private static MockWebServer server = new MockWebServer();
    private static ProxerConnection connection = new ProxerConnection.Builder("test",
            InstrumentationRegistry.getContext()).withDeliverCancelledRequests(true).build();

    @BeforeClass
    public static void setUpServer() throws IOException {
        server.start();
    }

    @AfterClass
    public static void tearDownServer() throws IOException {
        server.shutdown();
    }

    @Test
    public void testSuccessfulExecution() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        News[] news = connection.executeSynchronized(new NewsRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        Assert.assertNotNull(news);
    }

    @Test(timeout = 3000)
    public void testSuccessfulAsyncExecution() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        connection.execute(new NewsRequest(0)
                        .withCustomHost(buildHostUrl(server.url(URL))),
                new ProxerCallback<News[]>() {
                    @Override
                    public void onSuccess(News[] result) {
                        lock.countDown();
                    }
                }, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        Assert.fail(ERROR_EXCEPTION);
                    }
                });

        lock.await();
    }

    @Test
    public void testNetworkException() throws Exception {
        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))
                            .newBuilder()
                            .port(12345)
                            .build()));

            Assert.fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            Assert.assertEquals(ProxerException.NETWORK, exception.getErrorCode());
        }
    }

    @Test
    public void testProxerException() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_error)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            Assert.fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            Assert.assertEquals(ProxerException.PROXER, exception.getErrorCode());
        }
    }

    @Test
    public void testProxerExceptionMessage() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_error)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            Assert.fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            Assert.assertEquals(exception.getMessage(), "News konnten nicht abgerufen werden.");
        }
    }

    @Test
    public void testProxerExceptionCode() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_error)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            Assert.fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            Assert.assertEquals((Integer) ProxerException.NEWS, exception.getProxerErrorCode());
        }
    }

    @Test
    public void testUnparsableException() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            Assert.fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            Assert.assertEquals(ProxerException.UNPARSABLE, exception.getErrorCode());
        }
    }

    @Test
    public void testCancelledException() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        ProxerCall call = connection.execute(new NewsRequest(0)
                        .withCustomHost(buildHostUrl(server.url(URL))),
                new ProxerCallback<News[]>() {
                    @Override
                    public void onSuccess(News[] result) {
                        Assert.fail(ERROR_EXPECTED_EXCEPTION);
                    }
                }, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        if (exception.getErrorCode() == ProxerException.CANCELLED) {
                            lock.countDown();
                        } else {
                            Assert.fail(ERROR_EXPECTED_EXCEPTION);
                        }
                    }
                });

        call.cancel();
        lock.await();
    }
}
