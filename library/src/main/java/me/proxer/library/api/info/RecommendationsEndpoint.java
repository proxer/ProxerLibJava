package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.info.Recommendation;

import java.util.List;

/**
 * Endpoint for retrieving the most important information of an {@link Recommendation}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class RecommendationsEndpoint implements Endpoint<List<Recommendation>> {

    private final InternalApi internalApi;

    private final String id;

    RecommendationsEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<Recommendation>> build() {
        return internalApi.recommendations(id);
    }
}
