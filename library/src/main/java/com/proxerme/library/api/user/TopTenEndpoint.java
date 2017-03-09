package com.proxerme.library.api.user;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.enums.Category;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for requesting an user's top ten list.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class TopTenEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    /**
     * Sets the category to filter by.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Category category;

    TopTenEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<TopTenEntry>> build() {
        return internalApi.topTen(userId, username, category);
    }
}
