package me.proxer.library.api.chat;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.chat.ChatRoom;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class UserChatRoomsEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("chat_rooms.json")));

        final List<ChatRoom> result = api.chat()
                .userRooms()
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildFirstTestRoom());
        assertThat(result).last().isEqualTo(buildLastTestRoom());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("chat_rooms.json")));

        api.chat().userRooms()
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/chat/myrooms");
    }

    private ChatRoom buildFirstTestRoom() {
        return new ChatRoom("1", "Proxer.Me Hauptchat", "Willkommen im Proxer-Chat!",
                false, false);
    }

    private ChatRoom buildLastTestRoom() {
        return new ChatRoom("6", "Chat-Ankündigungen (21.03.18)", "",
                true, false);
    }
}
