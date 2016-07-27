package com.proxerme.library.connection.notifications.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

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

    private int page;

    /**
     * The constructor.
     *
     * @param page The page to load. The first page is 0. If the page is greater than the available
     *             pages, the API will return an empty answer.
     */
    public NewsRequest(@IntRange(from = 0) int page) {
        this.page = page;
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
    }
}
