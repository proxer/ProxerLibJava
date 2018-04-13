package me.proxer.library.api.chat;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.chat.ChatMessage;

import javax.annotation.Nullable;
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
public final class ChatMessagesEndpoint implements Endpoint<List<ChatMessage>> {

    private final InternalApi internalApi;

    /**
     * Sets the id of the room to load.
     */
    private final String roomId;

    /**
     * Sets the message id to load from.
     */
    @Nullable
    @Setter
    private String messageId;

    ChatMessagesEndpoint(final InternalApi internalApi, final String roomId) {
        this.internalApi = internalApi;
        this.roomId = roomId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<ChatMessage>> build() {
        return internalApi.messages(roomId, messageId);
    }
}
