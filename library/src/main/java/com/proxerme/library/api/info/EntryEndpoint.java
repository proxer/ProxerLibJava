package com.proxerme.library.api.info;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Entry;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Create class
 *
 * @author Ruben Gees
 */
public class EntryEndpoint {

    private final InternalApi internalApi;

    private final String id;

    EntryEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @NotNull
    public ProxerCall<Entry> build() {
        return internalApi.entry(id);
    }
}
