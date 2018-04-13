package me.proxer.library.api.chat;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.chat.ChatRoom;

import java.util.List;

/**
 * Endpoint for retrieving the public chat rooms.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class PublicChatRoomsEndpoint implements Endpoint<List<ChatRoom>> {

    private final InternalApi internalApi;

    PublicChatRoomsEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<ChatRoom>> build() {
        return internalApi.publicRooms();
    }
}
