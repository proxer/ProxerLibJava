package com.proxerme.library.api.messenger;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Endpoint for creating a new conference group with one or more other participants.
 * <p>
 * Upon success, the result is a single string with the id of the newly created conference.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CreateConferenceGroupEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String topic;
    private final String firstMessage;
    private final List<String> participants;

    CreateConferenceGroupEndpoint(@NotNull final InternalApi internalApi, @NotNull final String topic,
                                  @NotNull final String firstMessage, @NotNull final List<String> participants) {
        this.internalApi = internalApi;
        this.topic = topic;
        this.firstMessage = firstMessage;
        this.participants = participants;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<String> build() {
        return internalApi.createConferenceGroup(topic, firstMessage, participants);
    }
}
