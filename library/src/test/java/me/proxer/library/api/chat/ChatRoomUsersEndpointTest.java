package me.proxer.library.api.chat;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.chat.ChatRoomUser;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
class ChatRoomUsersEndpointTest extends ProxerTest {

    @Test
    void testDefault() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("chat_room_users.json")));

        final List<ChatRoomUser> result = api.chat()
                .roomUsers("123")
                .build()
                .execute();

        assertThat(result).first().isEqualTo(buildFirstUser());
        assertThat(result).last().isEqualTo(buildLastUser());
    }

    @Test
    void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("chat_room_users.json")));

        api.chat().roomUsers("12")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/chat/roomusers?room_id=12");
    }

    private ChatRoomUser buildFirstUser() {
        return new ChatRoomUser("730019", "Akaya-", "730019_0bvh9W.jpg",
                "My heart is sinking like a stone, in an ocean of it's own.", false);
    }

    private ChatRoomUser buildLastUser() {
        return new ChatRoomUser("229687", "Aver", "229687_FYkLhC.png", "....", true);
    }
}
