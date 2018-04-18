package me.proxer.library.api.chat;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for reporting a message to the moderators.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ReportChatMessageEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String messageId;
    private final String message;

    ReportChatMessageEndpoint(final InternalApi internalApi, final String messageId, final String message) {
        this.internalApi = internalApi;
        this.messageId = messageId;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.reportMessage(messageId, message);
    }
}
