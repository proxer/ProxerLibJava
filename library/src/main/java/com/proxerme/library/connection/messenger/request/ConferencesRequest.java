package com.proxerme.library.connection.messenger.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.MessengerRequest;
import com.proxerme.library.connection.messenger.entity.Conference;
import com.proxerme.library.connection.messenger.result.ConferencesResult;
import com.proxerme.library.parameters.ConferenceTypeParameter.ConferenceType;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for the conferences of the user. The user must be logged in for this API to return data.
 * This API uses pagination.
 *
 * @author Ruben Gees
 */
public class ConferencesRequest extends MessengerRequest<Conference[]> {

    private static final String ENDPOINT = "conferences";

    private static final String PAGE_PARAMETER = "p";
    private static final String TYPE_PARAMETER = "type";

    private int page;
    private String type;

    /**
     * The constructor.
     *
     * @param page The page to load.
     */
    public ConferencesRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    /**
     * The type to load.
     *
     * @param type The type.
     * @return This request.
     */
    public ConferencesRequest withType(@Nullable @ConferenceType String type) {
        this.type = type;

        return this;
    }

    @Override
    protected ProxerResult<Conference[]> parse(@NonNull Moshi moshi,
                                               @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(ConferencesResult.class).fromJson(body.source());
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
                new Pair<>(TYPE_PARAMETER, type)
        );
    }
}