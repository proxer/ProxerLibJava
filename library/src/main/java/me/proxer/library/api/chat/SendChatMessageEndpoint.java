package me.proxer.library.api.chat;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for sending a single message in a {@link me.proxer.library.entity.chat.ChatRoom}.
 * <p>
 * Upon success, the id of the message is returned.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class SendChatMessageEndpoint implements Endpoint<String> {

    private final InternalApi internalApi;

    private final String roomId;
    private final String message;

    SendChatMessageEndpoint(final InternalApi internalApi, final String roomId, final String message) {
        this.internalApi = internalApi;
        this.roomId = roomId;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<String> build() {
        return internalApi.sendMessage(roomId, message);
    }
}
