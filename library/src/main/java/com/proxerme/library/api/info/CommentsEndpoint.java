package com.proxerme.library.api.info;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Comment;
import com.proxerme.library.enums.CommentSortCriteria;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class CommentsEndpoint {

    private final InternalApi internalApi;

    private final String id;

    private Integer page;
    private Integer limit;
    private CommentSortCriteria sortCriteria;

    CommentsEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @NotNull
    public CommentsEndpoint page(@Nullable final Integer page) {
        this.page = page;

        return this;
    }

    @NotNull
    public CommentsEndpoint limit(@Nullable final Integer limit) {
        this.limit = limit;

        return this;
    }

    @NotNull
    public CommentsEndpoint sort(@Nullable final CommentSortCriteria criteria) {
        this.sortCriteria = criteria;

        return this;
    }

    @NotNull
    public ProxerCall<List<Comment>> build() {
        return internalApi.comments(id, page, limit, sortCriteria);
    }
}
