package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.entity.info.EntryCore;

/**
 * Endpoint for retrieving the most important information of an {@link Entry}.
 * <p>
 * If you need everything, consider using the {@link EntryEndpoint}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class EntryCoreEndpoint implements Endpoint<EntryCore> {

    private final InternalApi internalApi;

    private final String id;

    EntryCoreEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<EntryCore> build() {
        return internalApi.entryCore(id);
    }
}
