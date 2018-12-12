package me.proxer.library.api.user;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class LogoutEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));

        api.user().logout()
                .build()
                .execute();
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));

        api.user().logout()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/logout");
    }
}
