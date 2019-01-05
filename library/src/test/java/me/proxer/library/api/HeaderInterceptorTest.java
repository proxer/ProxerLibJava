package me.proxer.library.api;

import me.proxer.library.BuildConfig;
import me.proxer.library.ProxerTest;
import me.proxer.library.util.ProxerUrls;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Ruben Gees
 */
class HeaderInterceptorTest extends ProxerTest {

    @Test
    void testApiKey() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-key")).isEqualTo("mockKey");
    }

    @Test
    void testDefaultUserAgent() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("User-Agent"))
                .isEqualTo("ProxerLibJava/" + BuildConfig.VERSION);
    }

    @Test
    void testCustomUserAgent() throws IOException, ProxerException, InterruptedException {
        api = constructApi()
                .userAgent("test")
                .build();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("User-Agent")).isEqualTo("test");
    }

    @Test
    void testCorrectHeadersForCdn() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());
        api.client().newCall(new Request.Builder().url(ProxerUrls.cdnBase()
                .newBuilder()
                .addPathSegment("test")
                .build()).build()).execute();

        final Headers headers = server.takeRequest().getHeaders();

        assertThat(headers.get("proxer-api-key")).isNull();
        assertThat(headers.get("User-Agent")).startsWith("ProxerLibJava/");
    }

    @Test
    void testCorrectHeadersForStreamServer() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());
        api.client().newCall(new Request.Builder().url("https://stream.proxer.me/test").build()).execute();

        final Headers headers = server.takeRequest().getHeaders();

        assertThat(headers.get("proxer-api-key")).isNull();
        assertThat(headers.get("User-Agent")).startsWith("ProxerLibJava/");
    }

    @Test
    void testCorrectHeadersForSpecificStreamServer() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());
        api.client().newCall(new Request.Builder().url("https://s3-ps.proxer.me/test").build()).execute();

        final Headers headers = server.takeRequest().getHeaders();

        assertThat(headers.get("proxer-api-key")).isNull();
        assertThat(headers.get("User-Agent")).startsWith("ProxerLibJava/");
    }

    @Test
    void testCorrectHeadersForMangaServer() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());
        api.client().newCall(new Request.Builder().url("https://manga0.proxer.me/test").build()).execute();

        final Headers headers = server.takeRequest().getHeaders();

        assertThat(headers.get("proxer-api-key")).isNull();
        assertThat(headers.get("User-Agent")).startsWith("ProxerLibJava/");
    }

    @Test
    void testOtherHostThrows() {
        HeaderInterceptor interceptor = new HeaderInterceptor("mock-key", "mock-user-agent");
        Interceptor.Chain chain = mock(Interceptor.Chain.class);

        when(chain.request()).thenReturn(new Request.Builder().url("https://example.com").build());

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> interceptor.intercept(chain));
    }
}
