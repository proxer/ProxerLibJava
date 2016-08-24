package com.proxerme.library.connection.user.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.user.entitiy.ToptenEntry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class ToptenResult extends ProxerResult<ToptenEntry[]> {

    @Json(name = "data")
    private ToptenEntry[] entries;

    protected ToptenResult() {
    }

    @Override
    public ToptenEntry[] getData() {
        return entries;
    }
}
