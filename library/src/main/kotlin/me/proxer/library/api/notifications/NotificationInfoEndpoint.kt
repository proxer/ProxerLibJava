package me.proxer.library.api.notifications

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.notifications.NotificationInfo

/**
 * Endpoint for retrieving the notifications of an user.
 *
 * @author Ruben Gees
 */
class NotificationInfoEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<NotificationInfo> {

    override fun build(): ProxerCall<NotificationInfo> {
        return internalApi.notificationInfo()
    }
}
