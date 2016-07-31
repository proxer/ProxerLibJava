package com.proxerme.library.connection.list.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.list.result.MediaListResult;
import com.proxerme.library.connection.parameters.CategoryParameter.Category;
import com.proxerme.library.connection.parameters.MediumParameter.Medium;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * Request for all available Media, featuring options for categories and other restrictions. (Use
 * the withParameter builders)
 * This API uses pagination.
 *
 * @author Ruben Gees
 */

public class MediaListRequest extends ProxerRequest<MediaListResult> {

    private static final String MEDIA_LIST_URL = "/api/v1/list/entrylist";

    private static final String CATEGORY_FORM = "category";
    private static final String MEDIUM_FORM = "medium";
    private static final String SHOW_HENTAI_FORM = "isH";
    private static final String SEARCH_START_FORM = "start";
    private static final String PAGE_FORM = "p";
    private static final String LIMIT_FORM = "limit";

    @Nullable
    private String category;
    @Nullable
    private String medium;
    @Nullable
    private Boolean showHentai;
    @Nullable
    private String searchStartString;
    private int page;
    @Nullable
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load from.
     */
    public MediaListRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    /**
     * Sets the limit on how many items to load.
     *
     * @param limit The limit.
     * @return This Request.
     */
    public MediaListRequest withLimit(@IntRange(from = 1) @Nullable Integer limit) {
        this.limit = limit;

        return this;
    }

    /**
     * Sets an String to search for from the start. This is useful for first letter based lists.
     * You can pass 'nonAlpha' to get non alphabetical entries.
     *
     * @param searchStartString The String to search for.
     * @return This Request.
     */
    public MediaListRequest withSearchStartString(@Nullable String searchStartString) {
        this.searchStartString = searchStartString;

        return this;
    }

    /**
     * Sets if Hentai should be included in the list.
     *
     * @param showHentai True, if hentai should be included.
     * @return This Request.
     */
    public MediaListRequest withShowHentai(@Nullable Boolean showHentai) {
        this.showHentai = showHentai;

        return this;
    }

    /**
     * Sets the medium to load.
     *
     * @param medium The medium.
     * @return This Request.
     */
    public MediaListRequest withMedium(@Nullable @Medium String medium) {
        this.medium = medium;

        return this;
    }

    /**
     * Sets the category to load.
     *
     * @param category The category.
     * @return This request.
     */
    public MediaListRequest withCategory(@Nullable @Category String category) {
        this.category = category;

        return this;
    }

    @Override
    protected int getTag() {
        return ProxerTag.MEDIA_LIST;
    }

    @Override
    protected MediaListResult parse(@NonNull Response response) throws Exception {
        return response.asClass(MediaListResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + MEDIA_LIST_URL;
    }

    @Nullable
    @Override
    protected Form getBody() {
        Form form = new Form();

        if (category != null) {
            form.add(CATEGORY_FORM, category);
        }

        if (medium != null) {
            form.add(MEDIUM_FORM, medium);
        }

        if (showHentai != null) {
            form.add(SHOW_HENTAI_FORM, showHentai);
        }

        if (searchStartString != null) {
            form.add(SEARCH_START_FORM, searchStartString);
        }

        form.add(PAGE_FORM, page);

        if (limit != null) {
            form.add(LIMIT_FORM, limit);
        }

        return form;
    }
}
