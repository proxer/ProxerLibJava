package com.proxerme.library.connection.notifications.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.notifications.result.NewsErrorResult;
import com.proxerme.library.connection.notifications.result.NewsResult;
import com.proxerme.library.info.ProxerTag;
import com.proxerme.library.info.ProxerUrlHolder;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class NewsRequest extends ProxerRequest<NewsResult, NewsErrorResult> {

    private static final String NEWS_URL = "/api/v1/notifications/news?p=%s";

    private int page;

    public NewsRequest(@IntRange(from = 1) int page) {
        this.page = page;
    }

    @Override
    protected int getTag() {
        return ProxerTag.NEWS;
    }

    @Override
    protected NewsResult parse(Response response) throws Exception {
        return response.asClass(NewsResult.class);
    }

    @Override
    protected NewsErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new NewsErrorResult(exception);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + NEWS_URL;
    }

    @Nullable
    @Override
    protected String[] getParameters() {
        return new String[]{String.valueOf(page)};
    }
}
