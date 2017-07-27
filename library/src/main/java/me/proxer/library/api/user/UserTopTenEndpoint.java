package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.TopTenEntry;
import me.proxer.library.enums.Category;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting an user's top ten list.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserTopTenEndpoint implements Endpoint<List<TopTenEntry>> {

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
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Boolean includeHentai;

    UserTopTenEndpoint(@Nonnull final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<TopTenEntry>> build() {
        return internalApi.topTen(userId, username, category, includeHentai);
    }
}
