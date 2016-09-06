package com.proxerme.library.connection.ucp.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.ucp.entitiy.HistoryEntry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class HistoryResult extends ProxerResult<HistoryEntry[]> {

    @Json(name = "data")
    private HistoryEntry[] entries;

    protected HistoryResult() {

    }

    @Override
    public HistoryEntry[] getData() {
        return new HistoryEntry[0];
    }
}
