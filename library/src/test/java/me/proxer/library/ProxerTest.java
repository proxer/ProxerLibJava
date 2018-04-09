package me.proxer.library;

import me.proxer.library.api.ProxerApi;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import okio.Okio;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Base class for all tests. It provides a ready to use {@link MockWebServer}, an OkHttp client, configured to change
 * the host to that of the server and a {@link ProxerApi} instance. After each test, the server is shutdown.
 * <p>
 * Moreover it has the {@link #fromResource(String)} method for easily loading a test file.
 *
 * @author Ruben Gees
 */
public abstract class ProxerTest {

    protected MockWebServer server;
    protected OkHttpClient client;
    protected ProxerApi api;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    final HttpUrl oldUrl = chain.request().url();
                    final HttpUrl serverUrl = server.url(oldUrl.encodedPath());
                    final HttpUrl newUrl = oldUrl.newBuilder()
                            .scheme(serverUrl.scheme())
                            .host(serverUrl.host())
                            .port(serverUrl.port())
                            .build();

                    return chain.proceed(chain.request().newBuilder().url(newUrl).build());
                })
                .connectTimeout(500, TimeUnit.MILLISECONDS)
                .writeTimeout(500, TimeUnit.MILLISECONDS)
                .readTimeout(500, TimeUnit.MILLISECONDS)
                .build();
        api = constructApi().build();

        server.start();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    protected String fromResource(final String file) throws IOException {
        return Okio.buffer(Okio.source(getClass().getClassLoader().getResourceAsStream(file))).readUtf8();
    }

    protected ProxerApi.Builder constructApi() {
        return new ProxerApi.Builder("mockKey")
                .client(client);
    }
}
