package com.proxerme.library.connection.user.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Comment;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class UserCommentResult extends ProxerResult<Comment[]> {

    @Json(name = "data")
    private Comment[] comments;

    protected UserCommentResult() {
    }

    @Override
    public Comment[] getData() {
        return comments;
    }
}
