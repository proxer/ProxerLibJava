package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.IndustryCore;
import me.proxer.library.enums.Country;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving a list of industries.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class IndustryListEndpoint implements PagingLimitEndpoint<List<IndustryCore>> {

    private final InternalApi internalApi;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private String searchStart;

    /**
     * Sets the query to search for.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private String search;

    /**
     * Sets the country to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Country country;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer limit;

    IndustryListEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @Override
    @Nonnull
    public ProxerCall<List<IndustryCore>> build() {
        return internalApi.industryList(searchStart, search, country, page, limit);

    }
}
