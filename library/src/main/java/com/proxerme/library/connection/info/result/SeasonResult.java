package com.proxerme.library.connection.info.result;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.info.entity.Season;
import com.proxerme.library.interfaces.ProxerResult;
import com.proxerme.library.parameters.SeasonParameter;

/**
 * Class that represents the result of the {@link com.proxerme.library.connection.info.request.SeasonRequest}.
 *
 * @author Desnoo
 */
public class SeasonResult implements ProxerResult<Season[]> {

    @Body(name = "data")
    Season[] seasons;

    /**
     * Private Constructor.
     */
    SeasonResult() {
    }

    /**
     * The Constructor of an array of seasons.
     *
     * @param seasons An array of seasons.
     */
    public SeasonResult(@SeasonParameter.SeasonConstraint Season[] seasons) {
        this.seasons = seasons;
    }

    /**
     * Returns the requested seasons of the entry.
     *
     * @return the seasons of an entry.
     */
    @Override
    @SeasonParameter.SeasonConstraint
    public Season[] getItem() {
        return seasons;
    }
}
