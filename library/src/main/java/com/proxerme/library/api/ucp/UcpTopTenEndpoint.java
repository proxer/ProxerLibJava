package com.proxerme.library.api.ucp;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.ucp.UcpTopTenEntry;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Endpoint for unmarking an entry as favorite.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UcpTopTenEndpoint implements Endpoint {

    private final InternalApi internalApi;

    UcpTopTenEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<UcpTopTenEntry>> build() {
        return internalApi.topTen();
    }
}
