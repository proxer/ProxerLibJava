package com.proxerme.library.connection.list.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.list.entity.MediaListEntry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class MediaListResult extends ProxerResult<MediaListEntry[]> {

    @Json(name = "data")
    private MediaListEntry[] entries;

    private MediaListResult() {
    }

    @Override
    public MediaListEntry[] getData() {
        return entries;
    }
}
