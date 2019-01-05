package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void testHttpsUntouched() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        client.newCall(new Request.Builder().url("https://proxer.me").build()).execute();

        assertThat(server.takeRequest().getRequestUrl().isHttps()).isEqualTo(true);
    }

    @Test
    void testOtherHostThrows() {
        final HttpsEnforcingInterceptor interceptor = new HttpsEnforcingInterceptor();
        final Interceptor.Chain chain = mock(Interceptor.Chain.class);

        when(chain.request()).thenReturn(new Request.Builder().url("https://example.com").build());

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> interceptor.intercept(chain));
    }
}
