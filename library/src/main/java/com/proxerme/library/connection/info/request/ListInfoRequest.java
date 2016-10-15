package com.proxerme.library.connection.info.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.InfoRequest;
import com.proxerme.library.connection.info.entity.ListInfo;
import com.proxerme.library.connection.info.result.ListInfoResult;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for the available episodes and languages of an
 * {@link com.proxerme.library.connection.info.entity.Entry}. This API uses pagination.
 *
 * @author Ruben Gees
 */
public class ListInfoRequest extends InfoRequest<ListInfo> {

    private static final String ENDPOINT = "listinfo";

    private static final String ID_PARAMETER = "id";
    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";

    private String id;
    private int page;
    private Integer limit;

    /**
     * The constructor.
     *
     * @param id   The id.
     * @param page The page.
     */
    public ListInfoRequest(@NonNull String id, @IntRange(from = 0) int page) {
        this.id = id;
        this.page = page;
    }

    /**
     * Sets the maximum amount of items to retrieve.
     *
     * @param limit The limit.
     * @return This request.
     */
    public ListInfoRequest withLimit(@IntRange(from = 1) int limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected ProxerResult<ListInfo> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ListInfoResult.class).lenient().fromJson(body.source());
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
                new Pair<>(ID_PARAMETER, id),
                new Pair<>(PAGE_PARAMETER, page),
                new Pair<>(LIMIT_PARAMETER, limit)
        );
    }
}
