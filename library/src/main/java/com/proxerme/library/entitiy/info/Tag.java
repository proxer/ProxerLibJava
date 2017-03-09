package com.proxerme.library.entitiy.info;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.entitiy.interfaces.TimeItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * Entity representing a single tag.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Tag implements IdItem, TimeItem {

    /**
     * Returns the id of the associated entry.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "tid")
    private String id;

    /**
     * Returns the id of the associated tag entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "id")
    private String entryTagId;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "timestamp")
    private Date time;

    /**
     * Returns true, if this tag has been rated.
     */
    @Json(name = "rate_flag")
    private boolean rated;

    /**
     * Returns true, if this tag is a spoiler.
     */
    @Json(name = "spoiler_flag")
    private boolean spoiler;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tag")
    private String name;

    /**
     * Returns the description.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "description")
    private String description;
}
