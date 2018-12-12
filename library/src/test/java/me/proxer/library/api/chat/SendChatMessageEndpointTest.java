package me.proxer.library.api.chat;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class SendChatMessageEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final String result = api.chat()
                .sendMessage("123", "message")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.chat().sendMessage("123", "message")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/chat/newmessage");
    }

    @Test
    void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.chat().sendMessage("312", "someMessage")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("room_id=312&message=someMessage");
    }
}
