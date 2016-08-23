package com.proxerme.library.connection.messenger.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;
import com.squareup.moshi.Json;

public class SendMessageResult extends ProxerResult<String> {

    @Nullable
    @Json(name = "data")
    String errorMessage;

    private SendMessageResult() {
    }

    @Override
    @Nullable
    public String getData() {
        return errorMessage;
    }
}
