package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.user.UserComment;
import me.proxer.library.enums.Category;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserCommentsEndpoint implements PagingLimitEndpoint<List<UserComment>> {

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
    @Setter(onMethod = @__({@Override}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer limit;

    /**
     * Sets the minimum length to filter by.
     */
    @Nullable
    @Setter
    private Integer minimumLength;

    UserCommentsEndpoint(final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<UserComment>> build() {
        return internalApi.comments(userId, username, category, page, limit, minimumLength);
    }
}
