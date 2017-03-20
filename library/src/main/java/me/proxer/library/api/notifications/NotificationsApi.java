package me.proxer.library.api.notifications;

import org.jetbrains.annotations.NotNull;
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
    public NotificationsApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public NewsEndpoint news() {
        return new NewsEndpoint(internalApi);
    }
}
