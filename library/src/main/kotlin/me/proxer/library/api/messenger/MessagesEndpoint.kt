package me.proxer.library.api.messenger

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.messenger.Message

/**
 * Endpoint for retrieving the user's messages.
 *
 * Four types of this request exist:
 * 1) [conferenceId] = 0 and [messageId] = 0: Returns the last messages of the user from all conferences.
 * 2) [conferenceId] = 0 and [messageId] != 0: Returns the last messages before that of the passed [messageId] from all
 * conferences.
 * 3) [conferenceId] != 0 and [messageId] = 0: Returns the last messages of the conference, specified by the passed
 * [conferenceId].
 * 4) [conferenceId] != 0 and message != 0: Returns the last messages before that of the passed [messageId] of the
 * conference, specified by the passed [conferenceId].
 *
 * Moreover the [markAsRead] parameter controls if the conference should be marked as read in the cases 3) and 4).
 *
 * @author Ruben Gees
 */
class MessagesEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<List<Message>> {

    private var conferenceId: String? = null
    private var messageId: String? = null
    private var markAsRead: Boolean? = null

    /**
     * Sets the conference id of the conference to load.
     */
    fun conferenceId(conferenceId: String?) = this.apply { this.conferenceId = conferenceId }

    /**
     * Sets the message id to load from.
     */
    fun messageId(messageId: String?) = this.apply { this.messageId = messageId }

    /**
     * Sets if the conference should be marked as read. Defaults to true.
     */
    fun markAsRead(markAsRead: Boolean? = true) = this.apply { this.markAsRead = markAsRead }

    /**
     * {@inheritDoc}
     */
    override fun build(): ProxerCall<List<Message>> {
        return internalApi.messages(conferenceId, messageId, markAsRead)
    }
}
