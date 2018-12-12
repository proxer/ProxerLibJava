package me.proxer.library.api.messenger;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class CreateConferenceEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("create_conference.json")));

        final String result = api.messenger()
                .createConference("message", "someUser")
                .build()
                .execute();

        assertThat(result).isEqualTo("abcTest");
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("create_conference.json")));

        api.messenger().createConference("a", "b")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/messenger/newconference");
    }

    @Test
    void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("create_conference.json")));

        api.messenger().createConference("test", "testUser")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("text=test&username=testUser");
    }
}
