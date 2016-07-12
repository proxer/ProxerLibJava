package com.proxerme.library.connection.experimental.messages.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerException;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.experimental.messages.result.ConferencesErrorResult;
import com.proxerme.library.connection.experimental.messages.result.ConferencesResult;
import com.proxerme.library.info.ProxerUrlHolder;

import static com.proxerme.library.info.ProxerTag.CONFERENCES;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */

public class ConferencesRequest extends ProxerRequest<ConferencesResult, ConferencesErrorResult> {

    private static final String CONFERENCES_URL = "/messages?format=json&json=conferences&p=%s";

    private int page;

    public ConferencesRequest(@IntRange(from = 1) int page) {
        this.page = page;
    }

    @Override
    protected ConferencesResult parse(Response response) throws Exception {
        return response.asClass(ConferencesResult.class);
    }

    @Override
    protected int getTag() {
        return CONFERENCES;
    }

    @Override
    protected ConferencesErrorResult createErrorResult(@NonNull ProxerException exception) {
        return new ConferencesErrorResult(exception);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + CONFERENCES_URL;
    }

    @Nullable
    @Override
    protected String[] getParameters() {
        return new String[]{String.valueOf(page)};
    }
}
