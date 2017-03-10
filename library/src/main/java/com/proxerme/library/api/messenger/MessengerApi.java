package com.proxerme.library.api.messenger;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

import java.util.List;

/**
 * API for the Messenger class.
 *
 * @author Ruben Gees
 */
public final class MessengerApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public MessengerApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferencesEndpoint conferences() {
        return new ConferencesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public MessagesEndpoint messages() {
        return new MessagesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceInfoEndpoint conferenceInfo(@NotNull String id) {
        return new ConferenceInfoEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public CreateConferenceEndpoint createConference(@NotNull String firstMessage, @NotNull String username) {
        return new CreateConferenceEndpoint(internalApi, firstMessage, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public CreateConferenceGroupEndpoint createConferenceGroup(@NotNull final String topic,
                                                               @NotNull final String firstMessage,
                                                               @NotNull final List<String> participants) {
        return new CreateConferenceGroupEndpoint(internalApi, topic, firstMessage, participants);
    }
}
