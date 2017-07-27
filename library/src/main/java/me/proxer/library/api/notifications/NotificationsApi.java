package me.proxer.library.api.notifications;

import retrofit2.Retrofit;

import javax.annotation.Nonnull;

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
    public NotificationsApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public NewsEndpoint news() {
        return new NewsEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public NotificationsEndpoint notifications() {
        return new NotificationsEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public NotificationInfoEndpoint notificationInfo() {
        return new NotificationInfoEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public DeleteNotificationEndpoint deleteNotification(@Nonnull final String id) {
        return new DeleteNotificationEndpoint(internalApi, id);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public DeleteNotificationEndpoint deleteAllNotifications() {
        return new DeleteNotificationEndpoint(internalApi, "0");
    }
}
