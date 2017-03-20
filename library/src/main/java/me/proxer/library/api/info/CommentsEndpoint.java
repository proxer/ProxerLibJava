package me.proxer.library.api.info;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Comment;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.enums.CommentSortCriteria;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the comments of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CommentsEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    private final String id;

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
     * Sets criteria on how to sort the comments.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private CommentSortCriteria sort;

    CommentsEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Comment>> build() {
        return internalApi.comments(id, page, limit, sort);
    }
}
