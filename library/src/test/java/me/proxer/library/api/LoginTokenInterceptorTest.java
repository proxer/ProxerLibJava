package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException.ErrorType;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Ruben Gees
 */
public class LoginTokenInterceptorTest extends ProxerTest {

    @Test
    public void testTokenSetAfterLogin() throws IOException, ProxerException, InterruptedException {
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
    public void testTokenRemovedAfterLogout() throws IOException, ProxerException, InterruptedException {
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
    public void testTokenNotSetOnError() throws IOException, ProxerException, InterruptedException {
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
    public void testTokenNotRemovedOnError() throws IOException, ProxerException, InterruptedException {
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
    public void testTokenRemovedOnLoginError() throws IOException, ProxerException, InterruptedException {
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
    public void testMalformedResponse() throws IOException, InterruptedException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("login_malformed.json")));

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.user().login("test", "secret").build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.PARSING);
    }
}
