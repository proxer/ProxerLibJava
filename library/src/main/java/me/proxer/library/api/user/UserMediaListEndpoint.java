package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.UserMediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.UserMediaListFilterType;
import me.proxer.library.enums.UserMediaListSortCriteria;

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

    @Nullable
    private final String userId;

    @Nullable
    private final String username;

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
    @Setter
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
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

    UserMediaListEndpoint(final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<UserMediaListEntry>> build() {
        return internalApi.userMediaList(userId, username, category, page, limit, search, searchStart, filter, sort,
                includeHentai);
    }
}
