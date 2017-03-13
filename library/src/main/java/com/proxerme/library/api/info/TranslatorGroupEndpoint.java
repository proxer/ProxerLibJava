package com.proxerme.library.api.info;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.TranslatorGroup;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving all information of an {@link com.proxerme.library.entitiy.info.TranslatorGroup}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    TranslatorGroupEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<TranslatorGroup> build() {
        return internalApi.translatorGroup(id);
    }
}
