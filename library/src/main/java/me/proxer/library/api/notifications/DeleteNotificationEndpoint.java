package me.proxer.library.api.notifications;

import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

import javax.annotation.Nonnull;

/**
 * Endpoint for deleting a single or all notifications.
 *
 * @author Ruben Gees
 */
public class DeleteNotificationEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;

    DeleteNotificationEndpoint(@Nonnull final InternalApi internalApi, @Nonnull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<Void> build() {
        return internalApi.deleteNotification(id);
    }
}
