package me.proxer.library.api.notifications;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.notifications.Notification;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the notifications of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class NotificationsEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer limit;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Boolean markAsRead;

    NotificationsEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Notification>> build() {
        return internalApi.notifications(page, limit, markAsRead);
    }
}
