package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpsEnforcingInterceptorTest extends ProxerTest {

    @Test
    public void testHttpsUpgradeWeb() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    public void testHttpsUpgradeCdn() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://cdn.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    public void testHttpsUpgradeManga() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://manga1.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    public void testHttpsUpgradeStream() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://stream.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    public void testHttpsFileStreamUntouched() throws IOException, InterruptedException {
        startHttpOnlyServer();

        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://s1.stream.proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(false);
    }

    @Test
    public void testHttpsUntouched() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("https://proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    public void testOtherHostUntouched() throws IOException, InterruptedException {
        startHttpOnlyServer();

        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("http://example.com").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(false);
    }
}
