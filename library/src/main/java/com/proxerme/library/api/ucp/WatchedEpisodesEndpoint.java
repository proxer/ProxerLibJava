package com.proxerme.library.api.ucp;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for unmarking an entry as favorite.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class WatchedEpisodesEndpoint implements Endpoint {

    private final InternalApi internalApi;

    WatchedEpisodesEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Integer> build() {
        return internalApi.watchedEpisodes();
    }
}
