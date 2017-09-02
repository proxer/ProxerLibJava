package me.proxer.library.api.notifications;

import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for deleting a single or all notifications.
 *
 * @author Ruben Gees
 */
public final class DeleteNotificationEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;

    DeleteNotificationEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.deleteNotification(id);
    }
}
