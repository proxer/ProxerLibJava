package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Season;

/**
 * Entity holding the season of an entry.
 *
 * @author Ruben Gees
 */
@Value
public class EntrySeasonInfo implements ProxerIdItem {

    /**
     * Returns the type.
     */
    @Json(name = "id")
    private String id;

    /**
     * Returns the year.
     */
    @Json(name = "year")
    private int year;

    /**
     * Returns the actual season.
     */
    @Json(name = "season")
    private Season season;
}
