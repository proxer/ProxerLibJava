package com.proxerme.library.api.notifications;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class NotificationsApi {

    private final InternalApi internalApi;

    public NotificationsApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public NewsEndpoint news() {
        return new NewsEndpoint(internalApi);
    }
}
