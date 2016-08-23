package com.proxerme.library.connection.user.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.UserRequest;
import com.proxerme.library.connection.user.entitiy.UserMediaListEntry;
import com.proxerme.library.connection.user.result.UserMediaListResult;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.UserMediaSortParameter.UserMediaSortCriteria;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for the watched/read media of the user. This includes Anime series, OVAs, Manga, etc.
 * This API uses pagination, honors the visibility settings of the user and features sorting and
 * searching (Use the withParameter builders).
 *
 * @author Ruben Gees
 */
public class UserMediaListRequest extends UserRequest<UserMediaListEntry[]> {

    private static final String ENDPOINT = "list";

    private static final String USER_ID_PARAMETER = "uid";
    private static final String USERNAME_PARAMETER = "username";
    private static final String CATEGORY_PARAMETER = "kat";
    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";
    private static final String SEARCH_PARAMETER = "search";
    private static final String SEARCH_START_PARAMETER = "search_start";
    private static final String SORT_PARAMETER = "sort";

    @Nullable
    private String userId;
    @Nullable
    private String username;
    @Nullable
    private String category;
    private int page;
    @Nullable
    private Integer limit;
    @Nullable
    private String searchString;
    @Nullable
    private String searchStartString;
    @Nullable
    private String sortCriteria;

    /**
     * The constructor. You can choose if you want to pass the id or the name of
     * the user, but must pass at least one. If you pass both, the username is discarded by the API.
     *
     * @param userId   The id of the user.
     * @param username The name of the user.
     * @param page     The page to retrieve.
     */
    public UserMediaListRequest(@Nullable String userId, @Nullable String username,
                                @IntRange(from = 0) int page) {
        this.userId = userId;
        this.username = username;
        this.page = page;
    }

    /**
     * Builder method for setting the category.
     *
     * @param category The category to load.
     * @return This request.
     */
    public UserMediaListRequest withCategory(@Nullable @Category String category) {
        this.category = category;

        return this;
    }

    /**
     * Builder method for setting the maximum amount of entries retrieved.
     *
     * @param limit The limit.
     * @return This request.
     */
    public UserMediaListRequest withLimit(@IntRange(from = 1) @Nullable Integer limit) {
        this.limit = limit;

        return this;
    }

    /**
     * Builder method for setting a search String. Entries which contain this String at any location
     * are returned.
     *
     * @param searchString The search String.
     * @return This request.
     */
    public UserMediaListRequest withSearchString(@Nullable String searchString) {
        this.searchString = searchString;

        return this;
    }

    /**
     * Builder method for setting a search String. Entries which contain this String at the start
     * are returned.
     *
     * @param searchStartString The search String.
     * @return This request.
     */
    public UserMediaListRequest withSearchFromStartString(@Nullable String searchStartString) {
        this.searchStartString = searchStartString;

        return this;
    }

    /**
     * Builder method for setting the sort criteria.
     *
     * @param sortCriteria The sort criteria.
     * @return This request.
     */
    public UserMediaListRequest withSortCriteria(@Nullable @UserMediaSortCriteria String
                                                         sortCriteria) {
        this.sortCriteria = sortCriteria;

        return this;
    }

    @Override
    protected ProxerResult<UserMediaListEntry[]> parse(@NonNull Moshi moshi,
                                                       @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(UserMediaListResult.class).fromJson(body.source());
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
                new Pair<>(SEARCH_PARAMETER, searchString),
                new Pair<>(SEARCH_START_PARAMETER, searchStartString),
                new Pair<>(SORT_PARAMETER, sortCriteria)
        );
    }
}