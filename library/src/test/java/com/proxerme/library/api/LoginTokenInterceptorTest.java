package com.proxerme.library.api;

import com.proxerme.library.ProxerTest;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class LoginTokenInterceptorTest extends ProxerTest {

    @Test
    public void testTokenSetAfterLogin() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        api.user().login("test", "secret").build().execute();
        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isEqualTo("OmSjyOzMeyICUnErDD04lsDta7" +
                "REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXr" +
                "ZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwFFDI" +
                "7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6");
    }

    @Test
    public void testTokenRemovedAfterLogout() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        api.user().login("test", "secret").build().execute();
        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));
        api.user().logout().build().execute();
        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    public void testTokenNotSetOnError() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login_error.json")));

        try {
            api.user().login("test", "secret").build().execute();
        } catch (ProxerException ignored) {

        }

        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }

    @Test
    public void testTokenNotRemovedOnError() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        api.user().login("test", "secret").build().execute();
        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("logout_error.json")));

        try {
            api.user().logout().build().execute();
        } catch (ProxerException ignored) {

        }

        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isEqualTo("OmSjyOzMeyICUnErDD04lsDta7" +
                "REW2fIn6ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXr" +
                "ZUU1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwFFDI" +
                "7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6");
    }

    @Test
    public void testTokenRemovedOnLoginError() throws IOException, ProxerException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));
        api.user().login("test", "secret").build().execute();
        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("conferences_error.json")));

        try {
            api.messenger().conferences().build().execute();
        } catch (ProxerException ignored) {

        }

        server.takeRequest();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));
        api.notifications().news().build().execute();

        assertThat(server.takeRequest().getHeaders().get("proxer-api-token")).isNull();
    }
}
