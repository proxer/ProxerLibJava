package me.proxer.library.api.messenger;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for creating a new conference with a single other user.
 * <p>
 * Upon success, the result is a single string with the id of the newly created conference.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CreateConferenceEndpoint implements Endpoint<String> {

    private final InternalApi internalApi;

    private final String firstMessage;
    private final String username;

    CreateConferenceEndpoint(final InternalApi internalApi, final String firstMessage, final String username) {
        this.internalApi = internalApi;
        this.firstMessage = firstMessage;
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<String> build() {
        return internalApi.createConference(firstMessage, username);
    }
}
