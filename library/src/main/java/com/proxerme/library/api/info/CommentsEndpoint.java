package com.proxerme.library.api.info;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Comment;
import com.proxerme.library.enums.CommentSortCriteria;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the comments of an {@link com.proxerme.library.entitiy.info.Entry}.
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
