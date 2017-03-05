package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.enums.Category;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class TopTenEndpoint {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;
    private Category category;

    public TopTenEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
                          @Nullable final String username) {
        if (userId == null && username == null) {
            throw new IllegalArgumentException("You must pass either an userId or an username.");
        }

        this.internalApi = internalApi;
        this.userId = userId;
        this.username = username;
    }

    @NotNull
    public TopTenEndpoint category(@Nullable final Category category) {
        this.category = category;

        return this;
    }

    @NotNull
    public ProxerCall<List<TopTenEntry>> build() {
        return internalApi.topTen(userId, username, category);
    }
}
