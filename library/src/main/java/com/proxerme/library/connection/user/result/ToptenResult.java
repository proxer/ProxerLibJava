package com.proxerme.library.connection.user.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.squareup.moshi.Json;

public class ToptenResult extends ProxerResult<ToptenEntry[]> {

    @Json(name = "data")
    private ToptenEntry[] entries;

    private ToptenResult() {
    }

    @Override
    public ToptenEntry[] getData() {
        return entries;
    }
}
