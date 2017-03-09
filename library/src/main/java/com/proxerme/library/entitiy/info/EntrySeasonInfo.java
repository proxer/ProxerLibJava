package com.proxerme.library.entitiy.info;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.enums.Season;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity holding the season of an entry.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class EntrySeasonInfo implements IdItem {

    /**
     * Returns the type.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
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
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "season")
    private Season season;
}
