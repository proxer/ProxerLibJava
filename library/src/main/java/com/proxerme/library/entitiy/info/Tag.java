package com.proxerme.library.entitiy.info;

import com.proxerme.library.interfaces.IdItem;
import com.proxerme.library.interfaces.TimeItem;
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
     * @return The id of the associated entry.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "tid")
    private String id;

    /**
     * @return The id of the associated tag entry.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "id")
    private String entryTagId;

    /**
     * @return The time.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "timestamp")
    private Date time;

    /**
     * @return True, if this tag has been rated.
     */
    @Json(name = "rate_flag")
    private boolean rated;

    /**
     * @return True, if this tag is a spoiler.
     */
    @Json(name = "spoiler_flag")
    private boolean spoiler;

    /**
     * @return The name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "tag")
    private String name;

    /**
     * @return The description.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "description")
    private String description;
}
