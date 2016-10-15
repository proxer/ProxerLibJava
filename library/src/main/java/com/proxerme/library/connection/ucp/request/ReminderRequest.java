package com.proxerme.library.connection.ucp.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.UcpRequest;
import com.proxerme.library.connection.ucp.entitiy.Reminder;
import com.proxerme.library.connection.ucp.result.ReminderResult;
import com.proxerme.library.parameters.CategoryParameter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for retrieving reminders of the user. This API uses pagination and requires the user to
 * be logged in.
 *
 * @author Ruben Gees
 */
public class ReminderRequest extends UcpRequest<Reminder[]> {

    private static final String ENDPOINT = "reminder";

    private static final String PAGE_PARAMETER = "p";
    private static final String CATEGORY_PARAMETER = "kat";
    private static final String LIMIT_PARAMETER = "limit";

    private int page;
    private String category;
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load.
     */
    public ReminderRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    /**
     * Sets the category to load. Per default, both anime and manga will be loaded.
     *
     * @param category The category.
     * @return This request.
     */
    public ReminderRequest withCategory(@CategoryParameter.Category String category) {
        this.category = category;

        return this;
    }

    /**
     * Sets the maximum of reminders to load.
     *
     * @param limit The maximum amount.
     * @return This request.
     */
    public ReminderRequest withLimit(@IntRange(from = 1) int limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected ProxerResult<Reminder[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ReminderResult.class).lenient().fromJson(body.source());
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
                new Pair<>(CATEGORY_PARAMETER, category),
                new Pair<>(LIMIT_PARAMETER, limit)
        );
    }
}
