package com.proxerme.library.connection.user.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Comment;
import com.proxerme.library.connection.user.UserRequest;
import com.proxerme.library.connection.user.result.UserCommentResult;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.util.Utils;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for the comments of a {@link com.proxerme.library.connection.user.entitiy.User}.
 * Note: This API is currently incomplete.
 *
 * @author Ruben Gees
 */
public class UserCommentRequest extends UserRequest<Comment[]> {

    private static final String ENDPOINT = "comments";

    private static final String USER_ID_PARAMETER = "uid";
    private static final String USERNAME_PARAMETER = "username";
    private static final String CATEGORY_PARAMETER = "kat";
    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";
    private static final String LENGTH_PARAMETER = "length";

    @Nullable
    private String userId;
    @Nullable
    private String username;

    private String category;
    private Integer page;
    private Integer limit;
    private Integer length;

    /**
     * The constructor. You can either pass the id of the user or the name of the user. If you pass
     * both, the id will be used.
     *
     * @param userId   The id of the user.
     * @param username The name of the user.
     */
    public UserCommentRequest(@Nullable String userId, @Nullable String username) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * Set the category to request. The default is
     * {@link com.proxerme.library.parameters.CategoryParameter#ANIME}.
     *
     * @param category The category.
     * @return The modified request.
     */
    public UserCommentRequest withCategory(@Category String category) {
        this.category = category;

        return this;
    }

    /**
     * Sets the page to request the comments by the request.
     *
     * @param page The page to get the comments from.
     * @return The modified request.
     */
    public UserCommentRequest withPage(@IntRange(from = 0) int page) {
        this.page = page;

        return this;
    }

    /**
     * Sets the limit of comments to get per page by the request.
     *
     * @param limit The limit of comments to get from a page.
     * @return The modified request.
     */
    public UserCommentRequest withLimit(@IntRange(from = 0) int limit) {
        this.limit = limit;

        return this;
    }

    /**
     * Sets the maximum length of the comment to be included in the result.
     *
     * @param length The length.
     * @return The modified request.
     */
    public UserCommentRequest withLength(@IntRange(from = 0) int length) {
        this.length = length;

        return this;
    }

    @Override
    protected ProxerResult<Comment[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(UserCommentResult.class)
                .fromJson(Utils.parseCommentData(body.string()));
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(USER_ID_PARAMETER, userId),
                new Pair<>(USERNAME_PARAMETER, username),
                new Pair<>(CATEGORY_PARAMETER, category),
                new Pair<>(PAGE_PARAMETER, page),
                new Pair<>(LIMIT_PARAMETER, limit),
                new Pair<>(LENGTH_PARAMETER, length)
        );
    }
}
