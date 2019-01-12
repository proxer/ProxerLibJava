package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException.ErrorType;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Ruben Gees
 */
class LoginTokenInterceptorTest extends ProxerTest {

    @Test
    void testTokenSetAfterLogin() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.user().login("test", "secret").build().execute();
        api.notifications().news().build().execute();

        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isEqualTo("OmSjyOzMeyICUnErDD04lsDta7"
                + "REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXr"
                + "ZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwFFDI"
                + "7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6");
    }

    @Test
    void testTokenRemovedAfterLogout() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.user().login("test", "secret").build().execute();
        api.user().logout().build().execute();
        api.notifications().news().build().execute();

        server.takeRequest();
        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    void testTokenNotSetOnError() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login_error.json")));
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        try {
            api.user().login("test", "secret").build().execute();
        } catch (ProxerException ignored) {
        }

        api.notifications().news().build().execute();

        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    void testTokenNotRemovedOnError() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse().setBody(fromResource("logout_error.json")));
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.user().login("test", "secret").build().execute();

        try {
            api.user().logout().build().execute();
        } catch (ProxerException ignored) {
        }

        api.notifications().news().build().execute();

        server.takeRequest();
        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isEqualTo("OmSjyOzMeyICUnErDD04lsDta7"
                + "REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXr"
                + "ZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwFFDI"
                + "7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6");
    }

    @Test
    void testTokenRemovedOnLoginError() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse().setBody(fromResource("conferences_error.json")));
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.user().login("test", "secret").build().execute();

        try {
            api.messenger().conferences().build().execute();
        } catch (ProxerException ignored) {
        }

        api.notifications().news().build().execute();

        server.takeRequest();
        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    void testMalformedResponse() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("login_malformed.json")));

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.user().login("test", "secret").build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.PARSING);
    }

    @Test
    void testEmptyResponse() {
        server.enqueue(new MockResponse());

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.user().login("test", "secret").build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.PARSING);
    }

    @Test
    void testNoBodyResponse() throws IOException, InterruptedException {
        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("https://proxer.me/fake").build()).execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    void testTokenNotSetForLogin() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));

        api.user().login("test", "secret").build().execute();
        api.user().login("test", "secret").build().execute();

        server.takeRequest();

        assertThat(server.takeRequest().getHeader("proxer-api-token")).isNull();
    }

    @Test
    void testTokenNotSetForInvalidHost() throws IOException {
        final DefaultLoginTokenManager tokenManager = new DefaultLoginTokenManager();
        final LoginTokenInterceptor interceptor = Mockito.spy(new LoginTokenInterceptor(tokenManager));
        final Interceptor.Chain chain = mock(Interceptor.Chain.class);

        final Request request = new Request.Builder().url("https://example.com").build();
        final Response response = mock(Response.class);

        tokenManager.persist("mock-token");

        when(chain.request()).thenReturn(request);
        when(chain.proceed(notNull())).thenReturn(response);

        interceptor.intercept(chain);

        verify(chain).proceed(request);
    }

    @Test
    void testTokenNotSetForCdnHost() throws IOException, InterruptedException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse());

        api.user().login("test", "secret").build().execute();
        api.client().newCall(new Request.Builder().url("http://cdn.proxer.me").build()).execute();

        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    void testTokenNotSetForStreamHost() throws IOException, InterruptedException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));

        api.user().login("test", "secret").build().execute();

        server.enqueue(new MockResponse());

        api.client().newCall(new Request.Builder().url("http://s3-ps.proxer.me").build()).execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    void testTokenNotSetForMangaHost() throws IOException, InterruptedException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        server.enqueue(new MockResponse());

        api.user().login("test", "secret").build().execute();
        api.client().newCall(new Request.Builder().url("http://manga0.proxer.me").build()).execute();

        server.takeRequest();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }
}
