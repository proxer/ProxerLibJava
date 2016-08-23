package com.proxerme.library.connection.messenger.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.messenger.entity.Conference;
import com.squareup.moshi.Json;

public class ConferencesResult extends ProxerResult<Conference[]> {

    @Json(name = "data")
    private Conference[] conferences;

    private ConferencesResult() {
    }

    @Override
    public Conference[] getData() {
        return conferences;
    }
}
