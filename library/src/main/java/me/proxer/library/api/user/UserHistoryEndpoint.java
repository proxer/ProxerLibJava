package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.user.UserHistoryEntry;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting the history of the current user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserHistoryEndpoint implements PagingLimitEndpoint<List<UserHistoryEntry>> {

    private final InternalApi internalApi;

    @Nullable
    private final String userId;

    @Nullable
    private final String username;

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
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter
    private Boolean includeHentai;

    UserHistoryEndpoint(final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<UserHistoryEntry>> build() {
        return internalApi.history(userId, username, page, limit, includeHentai);
    }
}
