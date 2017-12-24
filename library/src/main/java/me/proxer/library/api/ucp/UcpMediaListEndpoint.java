package me.proxer.library.api.ucp;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.user.UserMediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.UserMediaListFilterType;
import me.proxer.library.enums.UserMediaListSortCriteria;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting the media list of the current user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UcpMediaListEndpoint implements PagingLimitEndpoint<List<UserMediaListEntry>> {

    private final InternalApi internalApi;

    /**
     * Sets the category to filter by.
     */
    @Nullable
    @Setter
    private Category category;

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

    /**
     * Sets the query to search for.
     */
    @Nullable
    @Setter
    private String search;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter
    private String searchStart;

    /**
     * Sets the filter.
     */
    @Nullable
    @Setter
    private UserMediaListFilterType filter;

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter
    private Boolean includeHentai;

    /**
     * Set the criteria for sorting.
     */
    @Nullable
    @Setter
    private UserMediaListSortCriteria sort;

    UcpMediaListEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<UserMediaListEntry>> build() {
        return internalApi.mediaList(category, page, limit, search, searchStart, filter, sort, includeHentai);
    }
}
