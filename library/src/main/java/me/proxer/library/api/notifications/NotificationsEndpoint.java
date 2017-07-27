package me.proxer.library.api.notifications;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.notifications.Notification;

import javax.annotation.Nonnull;
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
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer limit;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Boolean markAsRead;

    NotificationsEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<List<Notification>> build() {
        return internalApi.notifications(page, limit, markAsRead);
    }
}
