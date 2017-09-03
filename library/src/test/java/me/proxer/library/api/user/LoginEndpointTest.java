package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.user.User;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class LoginEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));

        final User result = api.user().login("test", "supersecret")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestUser());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));

        api.user().login("test", "supersecret")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/login");
    }

    @Test
    public void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("login.json")));

        api.user().login("test", "supersecret")
                .secretKey("secretkey")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("username=test&password=supersecret&secretkey=secretkey");
    }

    private User buildTestUser() {
        return new User("121658", "121658_VHuZqz.jpg", "OmSjyOzMeyICUnErDD04lsDta7REW2fIn6"
                + "ZWUxG96mIXHmplYymjYZK94BNXA1wloFSVcw3fTKdA6CT49ek7b4dfCYcdWQ0Xv2TFvTUoD8XGHOHP11Uc46rF4BSXrZU"
                + "U1LxwEqSgxNWdAC3ACWMF2di3N0Xe9S88BEBe3tuAfoNP1NpAIadJlwK9DHlLxqS83rl6VPD9bqXabkKTsYBOslW61fOwF"
                + "FDI7WLZLo8UM35XnPRPLsBdLwgJL5dpJQ6");
    }
}
