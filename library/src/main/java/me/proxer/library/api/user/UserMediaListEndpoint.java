package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.UserMediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.UserMediaListFilterType;
import me.proxer.library.enums.UserMediaListSortCriteria;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting the media list of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserMediaListEndpoint implements PagingLimitEndpoint<List<UserMediaListEntry>> {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    /**
     * Sets the category to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Category category;

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

    /**
     * Sets the query to search for.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private String search;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private String searchStart;

    /**
     * Sets the filter.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private UserMediaListFilterType filter;

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Boolean includeHentai;

    /**
     * Set the criteria for sorting.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private UserMediaListSortCriteria sort;

    UserMediaListEndpoint(@Nonnull final InternalApi internalApi, @Nullable final String userId,
                          @Nullable final String username) {
        if (userId == null && username == null) {
            throw new IllegalArgumentException("You must pass either an userId or an username.");
        }

        this.internalApi = internalApi;
        this.userId = userId;
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<List<UserMediaListEntry>> build() {
        return internalApi.userMediaList(userId, username, category, page, limit, search, searchStart, filter, sort,
                includeHentai);
    }
}
