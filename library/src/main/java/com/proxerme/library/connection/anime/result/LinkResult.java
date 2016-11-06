package com.proxerme.library.connection.anime.result;

import com.proxerme.library.connection.ProxerResult;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public class LinkResult extends ProxerResult<String> {

    @Json(name = "data")
    private String link;

    protected LinkResult() {
    }

    @Override
    public String getData() {
        return link;
    }
}
