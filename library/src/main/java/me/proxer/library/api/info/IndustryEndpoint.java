package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Industry;

/**
 * Endpoint for retrieving all information of an {@link Industry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class IndustryEndpoint implements Endpoint<Industry> {

    private final InternalApi internalApi;

    private final String id;

    IndustryEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Industry> build() {
        return internalApi.industry(id);
    }
}
