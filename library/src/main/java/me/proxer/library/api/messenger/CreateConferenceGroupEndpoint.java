package me.proxer.library.api.messenger;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Endpoint for creating a new conference group with one or more other participants.
 * <p>
 * Upon success, the result is a single string with the id of the newly created conference.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CreateConferenceGroupEndpoint implements Endpoint<String> {

    private final InternalApi internalApi;

    private final String topic;
    private final String firstMessage;
    private final List<String> participants;

    CreateConferenceGroupEndpoint(@Nonnull final InternalApi internalApi, @Nonnull final String topic,
                                  @Nonnull final String firstMessage, @Nonnull final List<String> participants) {
        this.internalApi = internalApi;
        this.topic = topic;
        this.firstMessage = firstMessage;
        this.participants = participants;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<String> build() {
        return internalApi.createConferenceGroup(topic, firstMessage, participants);
    }
}
