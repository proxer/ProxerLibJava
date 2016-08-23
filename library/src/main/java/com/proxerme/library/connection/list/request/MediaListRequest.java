package com.proxerme.library.connection.list.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.list.ListRequest;
import com.proxerme.library.connection.list.entity.MediaListEntry;
import com.proxerme.library.connection.list.result.MediaListResult;
import com.proxerme.library.parameters.CategoryParameter.Category;
import com.proxerme.library.parameters.MediumParameter.Medium;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for all available media, featuring options for categories and other restrictions. (Use
 * the withParameter builders)
 * This API uses pagination.
 *
 * @author Ruben Gees
 */
public class MediaListRequest extends ListRequest<MediaListEntry[]> {

    private static final String ENDPOINT = "entrylist";

    private static final String CATEGORY_PARAMETER = "kat";
    private static final String MEDIUM_PARAMETER = "medium";
    private static final String SHOW_HENTAI_PARAMETER = "isH";
    private static final String SEARCH_START_PARAMETER = "start";
    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";

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
    protected ProxerResult<MediaListEntry[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(MediaListResult.class).fromJson(body.source());
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
                new Pair<>(CATEGORY_PARAMETER, category),
                new Pair<>(MEDIUM_PARAMETER, medium),
                new Pair<>(SHOW_HENTAI_PARAMETER, showHentai),
                new Pair<>(SEARCH_START_PARAMETER, searchStartString),
                new Pair<>(PAGE_PARAMETER, page),
                new Pair<>(LIMIT_PARAMETER, limit)
        );
    }
}