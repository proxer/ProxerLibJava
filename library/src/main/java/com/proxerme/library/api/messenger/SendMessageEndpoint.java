package com.proxerme.library.api.messenger;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for sending a single message in a {@link com.proxerme.library.entitiy.messenger.Conference}. This can be a
 * group or a conversation with a single user.
 * <p>
 * Upon success, the result is null. In some cases, an error is not returned normally, but as the message returned from
 * the request.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class SendMessageEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String conferenceId;
    private final String message;

    SendMessageEndpoint(@NotNull final InternalApi internalApi, @NotNull final String conferenceId,
                        @NotNull final String message) {
        this.internalApi = internalApi;
        this.conferenceId = conferenceId;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<String> build() {
        return internalApi.sendMessage(conferenceId, message);
    }
}
