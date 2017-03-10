package com.proxerme.library.api.messenger;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * API for the Messenger class.
 *
 * @author Ruben Gees
 */
public final class MessengerApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public MessengerApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ConferencesEndpoint conferences() {
        return new ConferencesEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public MessagesEndpoint messages() {
        return new MessagesEndpoint(internalApi);
    }
}
