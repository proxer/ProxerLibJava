package com.proxerme.library.api;

import com.proxerme.library.ProxerTest;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class HeaderInterceptorTest extends ProxerTest {

    @Test
    public void testApiKey() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-key")).isEqualTo("mockKey");
    }

    @Test
    public void testDefaultUserAgent() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("User-Agent")).startsWith("ProxerLibJava");
    }

    @Test
    public void testCustomUserAgent() throws IOException, ProxerException, InterruptedException {
        api = new ProxerApi.Builder("mockKey")
                .userAgent("test")
                .okHttp(client)
                .build();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("User-Agent")).isEqualTo("test");
    }
}
