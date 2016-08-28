package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Comment;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class CommentResult extends ProxerResult<Comment[]> {

    @Json(name = "data")
    private Comment[] comments;

    protected CommentResult() {
    }

    @Override
    public Comment[] getData() {
        return comments;
    }
}
