package com.proxerme.library.api.messenger;

import com.proxerme.library.ProxerTest;
import com.proxerme.library.api.ProxerException;
import com.proxerme.library.entitiy.messenger.Message;
import com.proxerme.library.enums.Device;
import com.proxerme.library.enums.MessageAction;
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
