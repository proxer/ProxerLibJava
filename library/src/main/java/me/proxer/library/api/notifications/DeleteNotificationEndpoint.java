package me.proxer.library.api.notifications;

import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for deleting a single or all notifications.
 *
 * @author Ruben Gees
 */
public class DeleteNotificationEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    DeleteNotificationEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Void> build() {
        return internalApi.deleteNotification(id);
    }
}
