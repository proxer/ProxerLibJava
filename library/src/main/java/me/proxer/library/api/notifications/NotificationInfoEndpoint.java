package me.proxer.library.api.notifications;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.notifications.NotificationInfo;

import javax.annotation.Nonnull;

/**
 * Endpoint for retrieving the notifications of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class NotificationInfoEndpoint implements Endpoint {

    private final InternalApi internalApi;

    NotificationInfoEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<NotificationInfo> build() {
        return internalApi.notificationInfo();
    }
}
