package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.UserMediaListEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.UserMediaListSortCriteria;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class UserMediaListEndpoint {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    private Category category;
    private Integer page;
    private Integer limit;
    private String searchQuery;
    private String searchStartQuery;
    private UserMediaListSortCriteria sortCriteria;

    UserMediaListEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
                          @Nullable final String username) {
        if (userId == null && username == null) {
            throw new IllegalArgumentException("You must pass either an userId or an username.");
        }

        this.internalApi = internalApi;
        this.userId = userId;
        this.username = username;
    }

    @NotNull
    public UserMediaListEndpoint category(@Nullable final Category category) {
        this.category = category;

        return this;
    }

    @NotNull
    public UserMediaListEndpoint page(@Nullable final Integer page) {
        this.page = page;

        return this;
    }

    @NotNull
    public UserMediaListEndpoint limit(@Nullable final Integer limit) {
        this.limit = limit;

        return this;
    }

    @NotNull
    public UserMediaListEndpoint search(@Nullable final String query) {
        this.searchQuery = query;

        return this;
    }

    @NotNull
    public UserMediaListEndpoint searchStart(@Nullable final String query) {
        this.searchStartQuery = query;

        return this;
    }

    @NotNull
    public UserMediaListEndpoint sort(@Nullable final UserMediaListSortCriteria criteria) {
        this.sortCriteria = criteria;

        return this;
    }

    @NotNull
    public ProxerCall<List<UserMediaListEntry>> build() {
        return internalApi.userMediaList(userId, username, category, page, limit, searchQuery, searchStartQuery,
                sortCriteria);

    }
}
