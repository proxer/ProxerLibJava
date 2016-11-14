package com.proxerme.library.connection;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.connection.notifications.request.NewsRequest;
import com.proxerme.library.test.BuildConfig;
import com.proxerme.library.test.R;
import com.squareup.moshi.Moshi;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.proxerme.library.util.TestUtils.buildHostUrl;
import static com.proxerme.library.util.TestUtils.loadResponse;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

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
    private static final String ERROR_LISTENER_NOT_CALLED_BEFORE_CALLBACK =
            "Listener not called before callback.";
    private static final String ERROR_LISTENER_WAS_NOT_UNREGISTERED =
            "Listener was not unregistered.";
    private static final String ERROR_WRONG_LISTENER_CALLED = "Wrong listener called.";

    private static final String API_KEY = "test";

    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String DEFAULT_USER_AGENT = "ProxerLibAndroid/" + BuildConfig.VERSION_NAME;
    private static final String CUSTOM_USER_AGENT = "Test/123";

    private static final long TIMEOUT = 123456L;

    private static MockWebServer server = new MockWebServer();
    private static ProxerConnection connection = new ProxerConnection.Builder(API_KEY,
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
    public void testUserAgentHeader() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        connection.executeSynchronized(new NewsRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(DEFAULT_USER_AGENT, server.takeRequest().getHeader(USER_AGENT_HEADER));
    }

    @Test
    public void testCustomUserAgentHeader() throws Exception {
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .withCustomUserAgent(CUSTOM_USER_AGENT)
                .build();

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        testConnection.executeSynchronized(new NewsRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertEquals(CUSTOM_USER_AGENT, server.takeRequest().getHeader(USER_AGENT_HEADER));
    }

    @Test
    public void testApiKey() throws Exception {
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext()).build();

        assertSame(API_KEY, testConnection.getApiKey());
    }

    @Test
    public void testCustomMoshi() throws Exception {
        Moshi moshi = new Moshi.Builder().build();

        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .withCustomMoshi(moshi)
                .build();

        assertSame(moshi, testConnection.getMoshi());
    }

    @Test
    public void testCustomOkHttpClient() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .build();

        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .withCustomOkHttp(okHttpClient)
                .build();

        assertEquals(TIMEOUT, testConnection.getHttpClient().connectTimeoutMillis());
    }

    @Test
    public void testSuccessfulExecution() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        News[] news = connection.executeSynchronized(new NewsRequest(0)
                .withCustomHost(buildHostUrl(server.url(URL))));

        assertNotNull(news);

        server.takeRequest();
    }

    @Test(timeout = 3000)
    public void testSuccessfulAsyncExecution() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news)));

        connection.execute(new NewsRequest(0).withCustomHost(buildHostUrl(server.url(URL))),
                new ProxerCallback<News[]>() {
                    @Override
                    public void onSuccess(News[] result) {
                        lock.countDown();
                    }
                }, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        fail(ERROR_EXCEPTION);
                    }
                });

        lock.await();

        server.takeRequest();
    }

    @Test
    public void testNetworkException() throws Exception {
        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))
                            .newBuilder()
                            .port(12345)
                            .build()));

            fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            assertEquals(ProxerException.NETWORK, exception.getErrorCode());
        }
    }

    @Test
    public void testProxerException() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_error)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            assertEquals(ProxerException.PROXER, exception.getErrorCode());
        }

        server.takeRequest();
    }

    @Test
    public void testProxerExceptionMessage() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_error)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            assertEquals(exception.getMessage(), "News konnten nicht abgerufen werden.");
        }

        server.takeRequest();
    }

    @Test
    public void testProxerExceptionCode() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_error)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            assertEquals((Integer) ProxerException.NEWS, exception.getProxerErrorCode());
        }

        server.takeRequest();
    }

    @Test
    public void testUnparsableException() throws Exception {
        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));

        try {
            connection.executeSynchronized(new NewsRequest(0)
                    .withCustomHost(buildHostUrl(server.url(URL))));

            fail(ERROR_EXPECTED_EXCEPTION);
        } catch (ProxerException exception) {
            assertEquals(ProxerException.UNPARSABLE, exception.getErrorCode());
        }

        server.takeRequest();
    }

    @Test(timeout = 3000)
    public void testCancelledException() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        ProxerCall call = connection.execute(new NewsRequest(0)
                        .withCustomHost(buildHostUrl(server.url(URL))),
                new ProxerCallback<News[]>() {
                    @Override
                    public void onSuccess(News[] result) {
                        fail(ERROR_EXPECTED_EXCEPTION);
                    }
                }, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        if (exception.getErrorCode() == ProxerException.CANCELLED) {
                            lock.countDown();
                        } else {
                            fail(ERROR_EXPECTED_EXCEPTION);
                        }
                    }
                });

        call.cancel();
        lock.await();
    }

    @Test(timeout = 3000)
    public void testErrorListener() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .build();

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));
        testConnection.registerErrorListener(ProxerException.UNPARSABLE,
                new ProxerConnection.ErrorListener() {
                    @Override
                    public void onError(@NonNull ProxerException exception) {
                        assertEquals(exception.getErrorCode(), ProxerException.UNPARSABLE);

                        lock.countDown();
                    }
                });

        testConnection.execute(new NewsRequest(0).withCustomHost(buildHostUrl(server.url(URL))),
                null, null);

        lock.await();

        server.takeRequest();
    }

    @Test(timeout = 3000)
    public void testMultipleErrorListeners() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .build();

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));
        testConnection.registerErrorListener(ProxerException.UNPARSABLE,
                new ProxerConnection.ErrorListener() {
                    @Override
                    public void onError(@NonNull ProxerException exception) {
                        lock.countDown();
                    }
                });
        testConnection.registerErrorListener(ProxerException.NETWORK,
                new ProxerConnection.ErrorListener() {
                    @Override
                    public void onError(@NonNull ProxerException exception) {
                        fail(ERROR_WRONG_LISTENER_CALLED);
                    }
                });

        testConnection.execute(new NewsRequest(0).withCustomHost(buildHostUrl(server.url(URL))),
                null, null);

        lock.await();

        server.takeRequest();
    }

    @Test(timeout = 3000)
    public void testUnregisterErrorListener() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .build();

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));
        testConnection.registerErrorListener(ProxerException.UNPARSABLE,
                new ProxerConnection.ErrorListener() {
                    @Override
                    public void onError(@NonNull ProxerException exception) {
                        fail(ERROR_LISTENER_WAS_NOT_UNREGISTERED);
                    }
                });
        testConnection.unregisterErrorListener(ProxerException.UNPARSABLE);

        testConnection.execute(new NewsRequest(0).withCustomHost(buildHostUrl(server.url(URL))),
                null, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        lock.countDown();
                    }
                });

        lock.await();

        server.takeRequest();
    }

    @Test(timeout = 3000)
    public void testUnregisterAllErrorListeners() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .build();

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));
        testConnection.registerErrorListener(ProxerException.UNPARSABLE,
                new ProxerConnection.ErrorListener() {
                    @Override
                    public void onError(@NonNull ProxerException exception) {
                        fail(ERROR_LISTENER_WAS_NOT_UNREGISTERED);
                    }
                });
        testConnection.unregisterAllErrorListeners();

        testConnection.execute(new NewsRequest(0).withCustomHost(buildHostUrl(server.url(URL))),
                null, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        lock.countDown();
                    }
                });

        lock.await();

        server.takeRequest();
    }

    @Test(timeout = 3000)
    public void testErrorListenerCalledBeforeCallback() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);
        ProxerConnection testConnection = new ProxerConnection.Builder(API_KEY,
                InstrumentationRegistry.getContext())
                .build();

        server.enqueue(new MockResponse().setBody(loadResponse(R.raw.news_broken)));
        testConnection.registerErrorListener(ProxerException.UNPARSABLE,
                new ProxerConnection.ErrorListener() {
                    @Override
                    public void onError(@NonNull ProxerException exception) {
                        lock.countDown();
                    }
                });

        testConnection.execute(new NewsRequest(0).withCustomHost(buildHostUrl(server.url(URL))),
                null, new ProxerErrorCallback() {
                    @Override
                    public void onError(ProxerException exception) {
                        if (lock.getCount() > 1) {
                            fail(ERROR_LISTENER_NOT_CALLED_BEFORE_CALLBACK);
                        }

                        lock.countDown();
                    }
                });

        lock.await();

        server.takeRequest();
    }
}
