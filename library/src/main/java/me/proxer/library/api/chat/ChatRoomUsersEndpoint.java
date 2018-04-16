package me.proxer.library.api.chat;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.chat.ChatRoomUser;

import java.util.List;

/**
 * Endpoint for retrieving messages in a chat room.
 * <p>
 * This behaves differently based on the parameter messageId:
 * 1) messageId = 0: Returns the last messages from the chat room.
 * 2) messageId != 0: Returns all messages older than that passed from the chat room.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ChatRoomUsersEndpoint implements Endpoint<List<ChatRoomUser>> {

    private final InternalApi internalApi;

    /**
     * Sets the id of the room to load.
     */
    private final String roomId;

    ChatRoomUsersEndpoint(final InternalApi internalApi, final String roomId) {
        this.internalApi = internalApi;
        this.roomId = roomId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<ChatRoomUser>> build() {
        return internalApi.roomUsers(roomId);
    }
}
