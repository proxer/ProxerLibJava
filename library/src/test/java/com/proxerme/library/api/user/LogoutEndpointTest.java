package com.proxerme.library.api.user;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class LogoutEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));

        api.user().logout()
                .build()
                .execute();
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("logout.json")));

        api.user().logout()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/user/logout");
    }
}
