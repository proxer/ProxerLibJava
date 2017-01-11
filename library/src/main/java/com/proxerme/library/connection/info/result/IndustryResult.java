package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Industry;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class IndustryResult extends ProxerResult<Industry> {

    @Json(name = "data")
    private Industry industry;

    protected IndustryResult() {
    }

    @Override
    public Industry getData() {
        return industry;
    }
}
