package me.proxer.library.api.messenger;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.messenger.Message;
import me.proxer.library.enums.Device;
import me.proxer.library.enums.MessageAction;
import okhttp3.mockwebserver.MockResponse;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class MessagesEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("messages.json")));

        final List<Message> result = api.messenger()
                .messages()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildTestMessage());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("messages.json")));

        api.messenger().messages()
                .conferenceId("123")
                .messageId("321")
                .markAsRead(false)
                .build()
                .execute();

        Assertions.assertThat(server.takeRequest().getPath())
                .isEqualTo("/api/v1/messenger/messages?conference_id=123&message_id=321&read=false");
    }

    private Message buildTestMessage() {
        return new Message("5193325", "131029", "121658", "RubyGee", "RubyGee",
                MessageAction.ADD_USER, new Date(1480260735L * 1000), Device.UNSPECIFIED);
    }
}
