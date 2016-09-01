package com.proxerme.library.connection.messenger.result;

import android.support.annotation.Nullable;

import com.proxerme.library.connection.ProxerResult;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class NewConferenceResult extends ProxerResult<String> {

    @Nullable
    @Json(name = "data")
    private String conferenceId;

    protected NewConferenceResult() {
    }

    @Override
    @Nullable
    public String getData() {
        return conferenceId;
    }
}
