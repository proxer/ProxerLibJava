package com.proxerme.library.api.messenger;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class CreateConferenceGroupEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("create_conference.json")));

        final String result = api.messenger()
                .createConferenceGroup("topic", "message", Arrays.asList("someUser", "anotherUser"))
                .build()
                .execute();

        assertThat(result).isEqualTo("abcTest");
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("create_conference.json")));

        api.messenger().createConferenceGroup("a", "b", Collections.singletonList("user"))
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/messenger/newconferencegroup");
    }

    @Test
    public void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("create_conference.json")));

        api.messenger().createConferenceGroup("topic", "message", Arrays.asList(
                "someUser", "anotherUser", "testUser"
        )).build().execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("topic=topic&text=message&users=someUser&users=anotherUser&users=testUser");
    }
}
