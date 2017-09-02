package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;

import java.util.Date;

/**
 * Entity representing a single tag.
 *
 * @author Ruben Gees
 */
@Value
public class Tag implements ProxerIdItem, ProxerDateItem {

    /**
     * Returns the id of the associated entry.
     */
    @Json(name = "tid")
    private String id;

    /**
     * Returns the id of the associated tag entry.
     */
    @Json(name = "id")
    private String entryTagId;

    /**
     * Returns the time.
     */
    @Json(name = "timestamp")
    private Date date;

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
    @Json(name = "tag")
    private String name;

    /**
     * Returns the description.
     */
    @Json(name = "description")
    private String description;
}
