package com.proxerme.library.connection.user.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.parameters.CategoryParameter.Category;
import com.proxerme.library.connection.parameters.SortParameter.SortCriteria;
import com.proxerme.library.connection.user.result.UserMediaListResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for the watched/read media of the user. This includes Anime series, OVAs, Manga, etc.
 * This API uses pagination, honors the visibility settings of the User and features sorting and
 * searching (Use the withParameter builders).
 *
 * @author Ruben Gees
 */

public class UserMediaListRequest extends ProxerRequest<UserMediaListResult> {

    private static final String USER_MEDIA_LIST_URL = "/api/v1/user/list";

    private static final String USERID_FORM = "uid";
    private static final String USERNAME_FORM = "username";
    private static final String CATEGORY_FORM = "kat";
    private static final String PAGE_FORM = "p";
    private static final String LIMIT_FORM = "limit";
    private static final String SEARCH_FORM = "search";
    private static final String SEARCH_START_FORM = "search_start";
    private static final String SORT_FORM = "sort";

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
     * Builder method for setting the {@link Category}.
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
     * Builder method for setting {@link SortCriteria}.
     *
     * @param sortCriteria The sort criteria.
     * @return This request.
     */
    public UserMediaListRequest withSortCriteria(@Nullable @SortCriteria String sortCriteria) {
        this.sortCriteria = sortCriteria;

        return this;
    }

    @Override
    protected int getTag() {
        return ProxerTag.USER_MEDIA_LIST;
    }

    @Override
    protected UserMediaListResult parse(@NonNull Response response) throws Exception {
        return response.asClass(UserMediaListResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + USER_MEDIA_LIST_URL;
    }

    @Override
    protected void appendToBody(@NonNull Form form) {
        if (userId != null) {
            form.add(USERID_FORM, userId);
        }

        if (username != null) {
            form.add(USERNAME_FORM, username);
        }

        if (category != null) {
            form.add(CATEGORY_FORM, category);
        }

        form.add(PAGE_FORM, page);

        if (limit != null) {
            form.add(LIMIT_FORM, limit);
        }

        if (searchString != null) {
            form.add(SEARCH_FORM, searchString);
        }

        if (searchStartString != null) {
            form.add(SEARCH_START_FORM, searchStartString);
        }

        if (sortCriteria != null) {
            form.add(SORT_FORM, sortCriteria);
        }
    }
}
