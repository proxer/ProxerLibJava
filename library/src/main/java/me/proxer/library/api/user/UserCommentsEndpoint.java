package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.UserComment;
import me.proxer.library.enums.Category;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class UserCommentsEndpoint implements PagingEndpoint<List<UserComment>>, LimitEndpoint<List<UserComment>> {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    /**
     * Sets the category to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Category category;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer limit;

    /**
     * Sets the minimum length to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Integer minimumLength;

    UserCommentsEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<List<UserComment>> build() {
        return internalApi.comments(userId, username, category, page, limit, minimumLength);
    }
}
