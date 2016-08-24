package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.EntryCore;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class EntryCoreResult extends ProxerResult<EntryCore> {

    @Json(name = "data")
    private EntryCore entryCore;

    protected EntryCoreResult() {
    }

    @Override
    public EntryCore getData() {
        return entryCore;
    }
}
