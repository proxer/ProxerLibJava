package me.proxer.library.api.notifications;

import retrofit2.Retrofit;

/**
 * API for the Notifications class.
 *
 * @author Ruben Gees
 */
public final class NotificationsApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public NotificationsApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public NewsEndpoint news() {
        return new NewsEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public NotificationsEndpoint notifications() {
        return new NotificationsEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public NotificationInfoEndpoint notificationInfo() {
        return new NotificationInfoEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public DeleteNotificationEndpoint deleteNotification(final String id) {
        return new DeleteNotificationEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    public DeleteNotificationEndpoint deleteAllNotifications() {
        return new DeleteNotificationEndpoint(internalApi, "0");
    }
}
