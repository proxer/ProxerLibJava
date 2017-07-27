package me.proxer.library.api.messenger;

import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification;
import me.proxer.library.enums.MessageAction;
import me.proxer.library.util.ProxerUtils;
import retrofit2.Retrofit;

import javax.annotation.Nonnull;
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
    public MessengerApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferencesEndpoint conferences() {
        return new ConferencesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public MessagesEndpoint messages() {
        return new MessagesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceInfoEndpoint conferenceInfo(@Nonnull final String id) {
        return new ConferenceInfoEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public CreateConferenceEndpoint createConference(@Nonnull final String firstMessage,
                                                     @Nonnull final String username) {
        return new CreateConferenceEndpoint(internalApi, firstMessage, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public CreateConferenceGroupEndpoint createConferenceGroup(@Nonnull final String topic,
                                                               @Nonnull final String firstMessage,
                                                               @Nonnull final List<String> participants) {
        return new CreateConferenceGroupEndpoint(internalApi, topic, firstMessage, participants);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public SendMessageEndpoint sendMessage(@Nonnull final String conferenceId, @Nonnull final String message) {
        return new SendMessageEndpoint(internalApi, conferenceId, message);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public SendMessageEndpoint sendMessage(@Nonnull final String conferenceId, @Nonnull final MessageAction action,
                                           @Nonnull final String subject) {
        return new SendMessageEndpoint(internalApi, conferenceId, constructMessageFromAction(action, subject));
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceModificationEndpoint markConferenceAsRead(@Nonnull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.READ);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceModificationEndpoint unmarkConferenceAsRead(@Nonnull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNREAD);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceModificationEndpoint markConferenceAsBlocked(@Nonnull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.BLOCK);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceModificationEndpoint unmarkConferenceAsBlocked(@Nonnull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNBLOCK);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceModificationEndpoint markConferenceAsFavorite(@Nonnull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.FAVOUR);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ConferenceModificationEndpoint unmarkConferenceAsFavorite(@Nonnull final String id) {
        return new ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNFAVOUR);
    }

    private String constructMessageFromAction(@Nonnull final MessageAction action, @Nonnull final String subject) {
        if (action == MessageAction.NONE) {
            throw new IllegalArgumentException("Invalid action: " + action);
        }

        return "/" + ProxerUtils.getApiEnumName(action) + " " + subject;
    }
}
