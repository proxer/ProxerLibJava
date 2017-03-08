package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.Season;
import com.proxerme.library.interfaces.IdItem;
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
     * @return The type.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * @return The year.
     */
    @Json(name = "year")
    private int year;

    /**
     * @return The actual season..
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "season")
    private Season season;
}
