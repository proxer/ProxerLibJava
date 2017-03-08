package com.proxerme.library.api.info;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.EntryCore;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Create class
 *
 * @author Ruben Gees
 */
public class EntryCoreEndpoint {

    private final InternalApi internalApi;

    private final String id;

    EntryCoreEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @NotNull
    public ProxerCall<EntryCore> build() {
        return internalApi.entryCore(id);
    }
}
