package com.proxerme.library.connection.ucp.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.UcpRequest;
import com.proxerme.library.connection.ucp.entitiy.HistoryEntry;
import com.proxerme.library.connection.ucp.result.HistoryResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for the history of the user. The user needs to be signed in for this API.
 * This API uses pagination.
 *
 * @author Ruben Gees
 */
public class HistoryRequest extends UcpRequest<HistoryEntry[]> {

    private static final String ENDPOINT = "history";

    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";

    private int page;
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load from.
     */
    public HistoryRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    /**
     * The maximum amount of entries to load.
     *
     * @param limit The amount.
     * @return
     */
    public HistoryRequest withLimit(@IntRange(from = 0) int limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected ProxerResult<HistoryEntry[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(HistoryResult.class).fromJson(body.source());
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
                new Pair<>(PAGE_PARAMETER, page),
                new Pair<>(LIMIT_PARAMETER, limit)
        );
    }
}
