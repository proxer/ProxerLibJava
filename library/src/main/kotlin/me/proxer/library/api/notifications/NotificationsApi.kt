package me.proxer.library.api.notifications

import retrofit2.Retrofit

/**
 * API for the Notifications class.
 *
 * @author Ruben Gees
 */
class NotificationsApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun news(): NewsEndpoint {
        return NewsEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun notifications(): NotificationsEndpoint {
        return NotificationsEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun notificationInfo(): NotificationInfoEndpoint {
        return NotificationInfoEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun deleteNotification(id: String): DeleteNotificationEndpoint {
        return DeleteNotificationEndpoint(internalApi, id)
    }

    /**
     * Returns the respective endpoint.
     */
    fun deleteAllNotifications(): DeleteNotificationEndpoint {
        return DeleteNotificationEndpoint(internalApi, "0")
    }
}
