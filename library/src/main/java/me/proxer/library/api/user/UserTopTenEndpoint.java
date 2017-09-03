package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.user.TopTenEntry;
import me.proxer.library.enums.Category;

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
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter
    private Boolean includeHentai;

    UserTopTenEndpoint(final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<TopTenEntry>> build() {
        return internalApi.topTen(userId, username, category, includeHentai);
    }
}
