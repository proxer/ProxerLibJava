package com.proxerme.library.connection.notifications.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.notifications.result.NewsResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * The Request for obtaining the latest News. This API uses pagination.
 *
 * @author Ruben Gees
 */
public class NewsRequest extends ProxerRequest<NewsResult> {

    private static final String NEWS_URL = "/api/v1/notifications/news";

    private static final String PAGE_FORM = "p";
    private static final String LIMIT_FORM = "limit";

    private int page;
    @Nullable
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load. The first page is 0. If the page is greater than the available
     *             pages, the API will return an empty answer.
     */
    public NewsRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    /**
     * Builder method for setting the maximum amount of entries retrieved.
     *
     * @param limit The limit.
     * @return This request.
     */
    public NewsRequest withLimit(@IntRange(from = 1) @Nullable Integer limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected int getTag() {
        return ProxerTag.NEWS;
    }

    @Override
    protected NewsResult parse(@NonNull Response response) throws Exception {
        return response.asClass(NewsResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + NEWS_URL;
    }

    @Override
    protected void appendToBody(@NonNull Form form) {
        form.add(PAGE_FORM, page);

        if (limit != null) {
            form.add(LIMIT_FORM, limit);
        }
    }
}
