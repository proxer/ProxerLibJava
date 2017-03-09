package com.proxerme.library.api.info;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Entry;
import com.proxerme.library.entitiy.info.EntryCore;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving the most important information of an {@link Entry}.
 * <p>
 * If you need everything, consider using the {@link EntryEndpoint}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class EntryCoreEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    EntryCoreEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<EntryCore> build() {
        return internalApi.entryCore(id);
    }
}
