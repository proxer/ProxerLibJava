package com.proxerme.library.api.messenger;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class MessengerApi {

    private InternalApi internalApi;

    public MessengerApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public ConferencesEndpoint conferences() {
        return new ConferencesEndpoint(internalApi);
    }
}
