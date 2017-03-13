package com.proxerme.library.api.info;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Industry;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving all information of an {@link com.proxerme.library.entitiy.info.Industry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class IndustryEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    IndustryEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Industry> build() {
        return internalApi.industry(id);
    }
}
