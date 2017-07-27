package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.ucp.UcpTopTenEntry;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Endpoint for unmarking an entry as favorite.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UcpTopTenEndpoint implements Endpoint<List<UcpTopTenEntry>> {

    private final InternalApi internalApi;

    UcpTopTenEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<List<UcpTopTenEntry>> build() {
        return internalApi.topTen();
    }
}
