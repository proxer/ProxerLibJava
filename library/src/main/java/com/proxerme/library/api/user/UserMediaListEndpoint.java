package com.proxerme.library.api.user;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.UserMediaListEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.UserMediaListSortCriteria;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for requesting the media list of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserMediaListEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    /**
     * Sets the category to filter by.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Category category;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer limit;

    /**
     * Sets the query to search for.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String search;

    /**
     * Sets the query to search for only from the start.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String searchStart;

    /**
     * Set the criteria for sorting.
     */
    @Setter(onMethod = @__({@Nullable}))
    private UserMediaListSortCriteria sort;

    UserMediaListEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
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
    @NotNull
    public ProxerCall<List<UserMediaListEntry>> build() {
        return internalApi.userMediaList(userId, username, category, page, limit, search, searchStart, sort);
    }
}
