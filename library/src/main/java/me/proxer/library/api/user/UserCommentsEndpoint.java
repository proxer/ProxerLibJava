package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.UserComment;
import me.proxer.library.enums.Category;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class UserCommentsEndpoint implements PagingLimitEndpoint<List<UserComment>> {

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
     * Sets the minimum length to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Integer minimumLength;

    UserCommentsEndpoint(@Nonnull final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<UserComment>> build() {
        return internalApi.comments(userId, username, category, page, limit, minimumLength);
    }
}
