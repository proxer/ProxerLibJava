package com.proxerme.library.connection.messenger.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class SendMessageResult extends ProxerResult<String> {

    @Nullable
    @Json(name = "data")
    private String errorMessage;

    protected SendMessageResult() {
    }

    @Override
    @Nullable
    public String getData() {
        return errorMessage;
    }
}
