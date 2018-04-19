package me.proxer.library;

import me.proxer.library.api.ProxerApi;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.internal.tls.SslClient;
import okio.Okio;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

/**
 * Base class for all tests. It provides a ready to use {@link MockWebServer}, an OkHttp client, configured to change
 * the host to that of the server and a {@link ProxerApi} instance. After each test, the server is shutdown.
 * <p>
 * Moreover it has the {@link #fromResource(String)} method for easily loading a test file.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
public abstract class ProxerTest {

    private final SslClient sslClient = SslClient.localhost();

    protected MockWebServer server;
    protected OkHttpClient client;
    protected ProxerApi api;

    @Before
    public void setUp() throws IOException, GeneralSecurityException {
        api = constructApi().build();
        client = api.client();
        server = new MockWebServer();

        server.useHttps(sslClient.socketFactory, false);
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
                .client(new OkHttpClient.Builder()
                        .addInterceptor(chain -> {
                            final HttpUrl oldUrl = chain.request().url();
                            final HttpUrl serverUrl = server.url(oldUrl.encodedPath());
                            final HttpUrl newUrl = oldUrl.newBuilder()
                                    .scheme(oldUrl.scheme())
                                    .host(serverUrl.host())
                                    .port(serverUrl.port())
                                    .build();

                            return chain.proceed(chain.request().newBuilder().url(newUrl).build());
                        })
                        .hostnameVerifier((s, sslSession) -> true)
                        .sslSocketFactory(sslClient.socketFactory, sslClient.trustManager)
                        .connectTimeout(500, TimeUnit.MILLISECONDS)
                        .writeTimeout(500, TimeUnit.MILLISECONDS)
                        .readTimeout(500, TimeUnit.MILLISECONDS)
                        .build());
    }

    protected void startHttpOnlyServer() throws IOException {
        server.shutdown();

        server = new MockWebServer();

        server.start();
    }
}
