package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Entry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class EntryResult extends ProxerResult<Entry> {

    @Json(name = "data")
    private Entry entry;

    protected EntryResult() {
    }

    @Override
    public Entry getData() {
        return entry;
    }
}
