package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerDateItem;
import me.proxer.library.entitiy.ProxerIdItem;

import javax.annotation.Nonnull;
import java.util.Date;

/**
 * Entity representing a single tag.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Tag implements ProxerIdItem, ProxerDateItem {

    /**
     * Returns the id of the associated entry.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "tid")
    private String id;

    /**
     * Returns the id of the associated tag entry.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "id")
    private String entryTagId;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Nonnull}))
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
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "tag")
    private String name;

    /**
     * Returns the description.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "description")
    private String description;
}
