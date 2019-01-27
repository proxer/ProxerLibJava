package me.proxer.library.api.messenger

import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification
import me.proxer.library.enums.MessageAction
import me.proxer.library.util.ProxerUtils
import retrofit2.Retrofit

/**
 * API for the Messenger class.
 *
 * @author Ruben Gees
 */
class MessengerApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun conferences(): ConferencesEndpoint {
        return ConferencesEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun messages(): MessagesEndpoint {
        return MessagesEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun conferenceInfo(id: String): ConferenceInfoEndpoint {
        return ConferenceInfoEndpoint(internalApi, id)
    }

    /**
     * Returns the respective endpoint.
     */
    fun createConference(firstMessage: String, username: String): CreateConferenceEndpoint {
        return CreateConferenceEndpoint(internalApi, firstMessage, username)
    }

    /**
     * Returns the respective endpoint.
     */
    fun createConferenceGroup(
        topic: String,
        firstMessage: String,
        participants: List<String>
    ): CreateConferenceGroupEndpoint {
        return CreateConferenceGroupEndpoint(internalApi, topic, firstMessage, participants)
    }

    /**
     * Returns the respective endpoint.
     */
    fun sendMessage(conferenceId: String, message: String): SendMessageEndpoint {
        return SendMessageEndpoint(internalApi, conferenceId, message)
    }

    /**
     * Returns the respective endpoint.
     */
    fun sendMessage(conferenceId: String, action: MessageAction, subject: String): SendMessageEndpoint {
        return SendMessageEndpoint(internalApi, conferenceId, constructMessageFromAction(action, subject))
    }

    /**
     * Returns the respective endpoint.
     */
    fun markConferenceAsRead(id: String): ConferenceModificationEndpoint {
        return ConferenceModificationEndpoint(internalApi, id, ConferenceModification.READ)
    }

    /**
     * Returns the respective endpoint.
     */
    fun unmarkConferenceAsRead(id: String): ConferenceModificationEndpoint {
        return ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNREAD)
    }

    /**
     * Returns the respective endpoint.
     */
    fun markConferenceAsBlocked(id: String): ConferenceModificationEndpoint {
        return ConferenceModificationEndpoint(internalApi, id, ConferenceModification.BLOCK)
    }

    /**
     * Returns the respective endpoint.
     */
    fun unmarkConferenceAsBlocked(id: String): ConferenceModificationEndpoint {
        return ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNBLOCK)
    }

    /**
     * Returns the respective endpoint.
     */
    fun markConferenceAsFavorite(id: String): ConferenceModificationEndpoint {
        return ConferenceModificationEndpoint(internalApi, id, ConferenceModification.FAVOUR)
    }

    /**
     * Returns the respective endpoint.
     */
    fun unmarkConferenceAsFavorite(id: String): ConferenceModificationEndpoint {
        return ConferenceModificationEndpoint(internalApi, id, ConferenceModification.UNFAVOUR)
    }

    private fun constructMessageFromAction(action: MessageAction, subject: String): String {
        if (action == MessageAction.NONE) {
            throw IllegalArgumentException("Invalid action: $action")
        }

        return "/" + ProxerUtils.getSafeApiEnumName(action) + " " + subject
    }
}
