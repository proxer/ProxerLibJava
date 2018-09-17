package me.proxer.library.api.messenger;

import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification;
import me.proxer.library.enums.MessageAction;
import me.proxer.library.util.ProxerUtils;
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
    public MessengerApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferencesEndpoint conferences() {
        return new ConferencesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public MessagesEndpoint messages() {
        return new MessagesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceInfoEndpoint conferenceInfo(final String id) {
        return new ConferenceInfoEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    public CreateConferenceEndpoint createConference(final String firstMessage, final String username) {
        return new CreateConferenceEndpoint(internalApi, firstMessage, username);
    }

    /**
     * Returns the respective endpoint.
     */
    public CreateConferenceGroupEndpoint createConferenceGroup(final String topic, final String firstMessage,
                                                               final List<String> participants) {
        return new CreateConferenceGroupEndpoint(internalApi, topic, firstMessage, participants);
    }

    /**
     * Returns the respective endpoint.
     */
    public SendMessageEndpoint sendMessage(final String conferenceId, final String message) {
        return new SendMessageEndpoint(internalApi, conferenceId, message);
    }

    /**
     * Returns the respective endpoint.
     */
    public SendMessageEndpoint sendMessage(final String conferenceId, final MessageAction action,
                                           final String subject) {
        return new SendMessageEndpoint(internalApi, conferenceId, constructMessageFromAction(action, subject));
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceModificationEndpoint markConferenceAsRead(final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.READ);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceModificationEndpoint unmarkConferenceAsRead(final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNREAD);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceModificationEndpoint markConferenceAsBlocked(final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.BLOCK);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceModificationEndpoint unmarkConferenceAsBlocked(final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNBLOCK);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceModificationEndpoint markConferenceAsFavorite(final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.FAVOUR);
    }

    /**
     * Returns the respective endpoint.
     */
    public ConferenceModificationEndpoint unmarkConferenceAsFavorite(final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNFAVOUR);
    }

    private String constructMessageFromAction(final MessageAction action, final String subject) {
        if (action == MessageAction.NONE) {
            throw new IllegalArgumentException("Invalid action: " + action);
        }

        return "/" + ProxerUtils.getSafeApiEnumName(action) + " " + subject;
    }
}
