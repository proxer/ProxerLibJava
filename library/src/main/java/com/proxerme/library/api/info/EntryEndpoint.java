package com.proxerme.library.api.info;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Entry;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving all information of an {@link Entry}.
 * <p>
 * If you do not need everything, consider using the {@link EntryCoreEndpoint}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class EntryEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    EntryEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Entry> build() {
        return internalApi.entry(id);
    }
}
