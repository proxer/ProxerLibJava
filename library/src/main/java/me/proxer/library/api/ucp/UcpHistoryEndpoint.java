package me.proxer.library.api.ucp;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.ucp.UcpHistoryEntry;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting the history of the current user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UcpHistoryEndpoint implements PagingLimitEndpoint<List<UcpHistoryEntry>> {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer limit;

    UcpHistoryEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<UcpHistoryEntry>> build() {
        return internalApi.history(page, limit);
    }
}
