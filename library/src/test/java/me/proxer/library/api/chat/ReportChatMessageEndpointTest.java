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
class ReportChatMessageEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        final Void result = api.chat()
                .reportMessage("123", "message")
                .build()
                .execute();

        assertThat(result).isNull();
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.chat().reportMessage("123", "message")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/chat/reportmessage");
    }

    @Test
    void testParameters() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("empty.json")));

        api.chat().reportMessage("312", "someMessage")
                .build()
                .execute();

        assertThat(server.takeRequest().getBody().readUtf8())
                .isEqualTo("message_id=312&message=someMessage");
    }
}
