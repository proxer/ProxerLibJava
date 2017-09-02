package me.proxer.library.api.info;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Comment;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.enums.CommentSortCriteria;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving the comments of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CommentsEndpoint implements PagingLimitEndpoint<List<Comment>> {

    private final InternalApi internalApi;

    private final String id;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer limit;

    /**
     * Sets criteria on how to sort the comments.
     */
    @Nullable
    @Setter
    private CommentSortCriteria sort;

    CommentsEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<Comment>> build() {
        return internalApi.comments(id, page, limit, sort);
    }
}
