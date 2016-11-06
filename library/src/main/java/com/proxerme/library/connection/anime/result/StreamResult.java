package com.proxerme.library.connection.anime.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.anime.entity.Stream;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class StreamResult extends ProxerResult<Stream[]> {

    @Json(name = "data")
    private Stream[] streams;

    protected StreamResult() {
    }

    @Override
    public Stream[] getData() {
        return streams;
    }
}
