package com.proxerme.library.api.ucp;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.ucp.UcpHistoryEntry;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for unmarking an entry as favorite.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UcpHistoryEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer limit;

    UcpHistoryEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<UcpHistoryEntry>> build() {
        return internalApi.history(page, limit);
    }
}
