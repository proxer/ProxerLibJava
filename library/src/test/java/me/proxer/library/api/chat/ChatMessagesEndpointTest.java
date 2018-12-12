package me.proxer.library.api.chat;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.chat.ChatMessage;
import me.proxer.library.enums.ChatMessageAction;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class ChatMessagesEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("chat_messages.json")));

        final List<ChatMessage> result = api.chat()
                .messages("123")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildFirstMessage());
        assertThat(result).last().isEqualTo(buildLastMessage());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("chat_messages.json")));

        api.chat().messages("12")
                .messageId("21")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/chat/messages?room_id=12&message_id=21");
    }

    private ChatMessage buildFirstMessage() {
        return new ChatMessage("777191", "62", "genesis", "62_RvGnYl.png",
                "testttt", ChatMessageAction.NONE, new Date(1523608207L * 1000));
    }

    private ChatMessage buildLastMessage() {
        return new ChatMessage("777189", "62", "genesis", "62_RvGnYl.png",
                "777186", ChatMessageAction.REMOVE_MESSAGE, new Date(1523608185L * 1000));
    }
}
