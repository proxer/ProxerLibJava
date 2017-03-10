package com.proxerme.library.api.messenger;

import com.proxerme.library.enums.MessageAction;
import com.proxerme.library.util.Utils;
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

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public SendMessageEndpoint sendMessage(@NotNull final String conferenceId, @NotNull final String message) {
        return new SendMessageEndpoint(internalApi, conferenceId, message);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public SendMessageEndpoint sendMessage(@NotNull final String conferenceId, @NotNull final MessageAction action,
                                           @NotNull String subject) {
        return new SendMessageEndpoint(internalApi, conferenceId, constructMessageFromAction(action, subject));
    }

    private String constructMessageFromAction(@NotNull final MessageAction action, @NotNull final String subject) {
        if (action == MessageAction.NONE) {
            throw new IllegalArgumentException("Invalid action: " + action);
        }

        try {
            return "/" + Utils.getEnumName(action) + " " + subject;
        } catch (NoSuchFieldException exception) {
            throw new IllegalArgumentException("Invalid action: " + action);
        }
    }
}
