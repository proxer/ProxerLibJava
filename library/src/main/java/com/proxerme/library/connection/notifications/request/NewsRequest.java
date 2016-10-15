package com.proxerme.library.connection.notifications.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.notifications.NotificationsRequest;
import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.connection.notifications.result.NewsResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * The Request for obtaining the latest News. This API uses pagination.
 *
 * @author Ruben Gees
 */
public class NewsRequest extends NotificationsRequest<News[]> {

    private static final String ENDPOINT = "news";

    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";

    private int page;
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load.
     */
    public NewsRequest(int page) {
        this.page = page;
    }

    /**
     * Builder method for setting the limit of the returned array size.
     *
     * @param limit The limit.
     * @return This request.
     */
    public NewsRequest withLimit(@IntRange(from = 1) int limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected ProxerResult<News[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(NewsResult.class).lenient().fromJson(body.source());
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
                new Pair<String, Object>(PAGE_PARAMETER, page),
                new Pair<String, Object>(LIMIT_PARAMETER, limit)
        );
    }
}
