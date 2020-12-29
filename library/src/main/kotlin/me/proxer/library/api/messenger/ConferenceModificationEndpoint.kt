package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification.BLOCK
import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification.FAVOUR
import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification.READ
import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification.UNBLOCK
import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification.UNFAVOUR
import me.proxer.library.api.messenger.ConferenceModificationEndpoint.ConferenceModification.UNREAD

/**
 * Endpoint for modifying a conference in various ways.
 *
 * Possible modifications are:
 * - Mark as read
 * - Mark as unread
 * - Block
 * - Unblock
 * - Mark as favourite
 * - Unmark as favourite
 *
 * @author Ruben Gees
 */
class ConferenceModificationEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String,
    private val modification: ConferenceModification
) : Endpoint<Unit?> {

    override fun build(): ProxerCall<Unit?> {
        return when (modification) {
            READ -> internalApi.markConferenceAsRead(id)
            UNREAD -> internalApi.unmarkConferenceAsRead(id)
            BLOCK -> internalApi.markConferenceAsBlocked(id)
            UNBLOCK -> internalApi.unmarkConferenceAsBlocked(id)
            FAVOUR -> internalApi.markConferenceAsFavorite(id)
            UNFAVOUR -> internalApi.unmarkConferenceAsFavorite(id)
        }
    }

    internal enum class ConferenceModification {
        READ, UNREAD, BLOCK, UNBLOCK, FAVOUR, UNFAVOUR
    }
}
