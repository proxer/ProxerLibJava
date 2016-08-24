package com.proxerme.library.connection.info.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.info.entity.Season;
import com.squareup.moshi.Json;

/**
 * Class that represents the result of the
 * {@link com.proxerme.library.connection.info.request.SeasonRequest}.
 *
 * @author Desnoo
 */
public final class SeasonResult extends ProxerResult<Season[]> {

    @Json(name = "data")
    private Season[] seasons;

    /**
     * Private Constructor.
     */
    private SeasonResult() {
    }

    @Override
    public Season[] getData() {
        return seasons;
    }
}
