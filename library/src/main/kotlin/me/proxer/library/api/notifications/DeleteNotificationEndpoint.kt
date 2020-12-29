package me.proxer.library.api.notifications

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for deleting a single or all notifications.
 *
 * @author Ruben Gees
 */
class DeleteNotificationEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Unit?> {

    override fun build(): ProxerCall<Unit?> {
        return internalApi.deleteNotification(id)
    }
}
