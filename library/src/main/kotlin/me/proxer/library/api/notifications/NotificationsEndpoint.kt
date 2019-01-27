package me.proxer.library.api.notifications

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.notifications.Notification
import me.proxer.library.enums.NotificationFilter

/**
 * Endpoint for retrieving the notifications of an user.
 *
 * @author Ruben Gees
 */
class NotificationsEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<Notification>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var markAsRead: Boolean? = null
    private var filter: NotificationFilter? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets if the retrieved notifications should be marked as read.
     */
    fun markAsRead(markAsRead: Boolean? = true) = this.apply { this.markAsRead = markAsRead }

    /**
     * Sets a filter for the notifications to retrieve.
     */
    fun filter(filter: NotificationFilter?) = this.apply { this.filter = filter }

    override fun build(): ProxerCall<List<Notification>> {
        return internalApi.notifications(page, limit, markAsRead, filter)
    }
}
