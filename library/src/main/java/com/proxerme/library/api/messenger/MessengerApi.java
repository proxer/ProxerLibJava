package com.proxerme.library.api.messenger;

import com.proxerme.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification;
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
    public ConferenceInfoEndpoint conferenceInfo(@NotNull final String id) {
        return new ConferenceInfoEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public CreateConferenceEndpoint createConference(@NotNull final String firstMessage,
                                                     @NotNull final String username) {
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
                                           @NotNull final String subject) {
        return new SendMessageEndpoint(internalApi, conferenceId, constructMessageFromAction(action, subject));
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceModificationEndpoint markConferenceAsRead(@NotNull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.READ);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceModificationEndpoint unmarkConferenceAsRead(@NotNull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNREAD);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceModificationEndpoint markConferenceAsBlocked(@NotNull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.BLOCK);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceModificationEndpoint unmarkConferenceAsBlocked(@NotNull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNBLOCK);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceModificationEndpoint markConferenceAsFavorite(@NotNull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.FAVOUR);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferenceModificationEndpoint unmarkConferenceAsFavorite(@NotNull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNFAVOUR);
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
