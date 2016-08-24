package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Season;
import com.squareup.moshi.Json;

/**
 * {@inheritDoc}
 */
public final class SeasonResult extends ProxerResult<Season[]> {

    @Json(name = "data")
    private Season[] seasons;

    protected SeasonResult() {
    }

    @Override
    public Season[] getData() {
        return seasons;
    }
}
