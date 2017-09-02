package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Entry;

/**
 * Endpoint for retrieving all information of an {@link Entry}.
 * <p>
 * If you do not need everything, consider using the {@link EntryCoreEndpoint}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class EntryEndpoint implements Endpoint<Entry> {

    private final InternalApi internalApi;

    private final String id;

    EntryEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Entry> build() {
        return internalApi.entry(id);
    }
}
