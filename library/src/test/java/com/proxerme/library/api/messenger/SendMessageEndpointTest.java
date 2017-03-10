package com.proxerme.library.api.messenger;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.enums.MessageAction;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class SendMessageEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("send_message.json")));

        final String result = api.messenger()
                .sendMessage("123", "message")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("send_message.json")));

        api.messenger().sendMessage("123", "message")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/messenger/setmessage");
    }

    @Test
    public void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("send_message.json")));

        api.messenger().sendMessage("id", "someMessage")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("conference_id=id&text=someMessage");
    }

    @Test
    public void testActionParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("send_message.json")));

        api.messenger().sendMessage("id", MessageAction.REMOVE_USER, "Testerio")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("conference_id=id&text=%2FremoveUser%20Testerio");
    }
}
