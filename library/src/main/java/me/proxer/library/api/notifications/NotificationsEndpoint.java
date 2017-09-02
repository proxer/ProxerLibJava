package me.proxer.library.api.notifications;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.notifications.Notification;
import me.proxer.library.enums.NotificationFilter;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving the notifications of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class NotificationsEndpoint implements PagingLimitEndpoint<List<Notification>> {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer limit;

    /**
     * Sets if the retrieved notifications should be marked as read.
     */
    @Nullable
    @Setter
    private Boolean markAsRead;

    /**
     * Sets a filter for the notifications to retrieve.
     */
    @Nullable
    @Setter
    private NotificationFilter filter;

    NotificationsEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<Notification>> build() {
        return internalApi.notifications(page, limit, markAsRead, filter);
    }
}
