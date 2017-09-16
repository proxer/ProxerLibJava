package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Season;

import javax.annotation.Nullable;

/**
 * Entity holding the season of an entry.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class EntrySeasonInfo implements ProxerIdItem {

    /**
     * Returns the type.
     */
    @Getter(onMethod = @__({@Override}))
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
