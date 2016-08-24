package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.EntryDetails;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class EntryDetailsResult extends ProxerResult<EntryDetails> {

    @Json(name = "data")
    private EntryDetails details;

    /**
     * Private constructor.
     */
    private EntryDetailsResult() {
    }

    @Override
    public EntryDetails getData() {
        return details;
    }
}
