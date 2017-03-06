package com.proxerme.library;

import com.proxerme.library.api.ProxerApi;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public abstract class EndpointTest {

    protected MockWebServer server;
    protected ProxerApi api;

    @Before
    public void setUp() throws IOException {
        server = new MockWebServer();
        api = new ProxerApi.Builder("mockKey")
                .okHttp(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            final HttpUrl oldUrl = chain.request().url();
                            final HttpUrl serverUrl = server.url(oldUrl.encodedPath());
                            final HttpUrl newUrl = oldUrl.newBuilder()
                                    .scheme(serverUrl.scheme())
                                    .host(serverUrl.host())
                                    .port(serverUrl.port())
                                    .build();

                            return chain.proceed(chain.request().newBuilder().url(newUrl).build());
                        }).build())
                .build();

        server.start();
    }

    @After
    public void tearDown() throws IOException {
        server.shutdown();
    }

    @NotNull
    protected String fromResource(@NotNull final String file) throws IOException {
        return Okio.buffer(Okio.source(getClass().getClassLoader().getResourceAsStream(file))).readUtf8();
    }
}
