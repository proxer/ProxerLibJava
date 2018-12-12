package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class HttpsEnforcingInterceptorTest extends ProxerTest {

    @Test
    void testHttpsUpgradeWeb() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testHttpsUpgradeCdn() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://cdn.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testHttpsUpgradeProxy() throws IOException, InterruptedException {
        startHttpOnlyServer();

        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://prxr.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(false);
    }

    @Test
    void testHttpsUpgradeManga() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://manga1.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testHttpsUpgradeStreamHtml() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://stream.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testHttpsUpgradeStreamAlternative() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://s1-ps.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testHttpsUpgradeStreamSimilarUntouched() throws IOException, InterruptedException {
        startHttpOnlyServer();

        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://s1-pss.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(false);
    }

    @Test
    void testHttpsUntouched() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("https://proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testOtherHostUntouched() throws IOException, InterruptedException {
        startHttpOnlyServer();

        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://example.com").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(false);
    }
}
