package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for requesting the amount of watched episodes of the current user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class WatchedEpisodesEndpoint implements Endpoint<Integer> {

    private final InternalApi internalApi;

    WatchedEpisodesEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Integer> build() {
        return internalApi.watchedEpisodes();
    }
}
