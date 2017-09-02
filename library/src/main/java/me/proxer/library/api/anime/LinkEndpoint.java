package me.proxer.library.api.anime;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for retrieving the link to the uploaded anime. This may be null, if the link is broken or has been deleted.
 * <p>
 * Note, that these links point to the web page and not the final data source.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class LinkEndpoint implements Endpoint<String> {

    private final InternalApi internalApi;

    private final String id;

    LinkEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<String> build() {
        return internalApi.link(id);
    }
}
