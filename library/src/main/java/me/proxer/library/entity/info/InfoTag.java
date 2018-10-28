package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.api.NumberBasedBoolean;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity representing a single tag in the context of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class InfoTag implements ProxerIdItem, ProxerDateItem {

    /**
     * Returns the id of the associated entry.
     */
    @Getter(onMethod = @__({@Override}))
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
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp")
    private Date date;

    /**
     * Returns true, if this tag has been rated.
     */
    @NumberBasedBoolean
    @Json(name = "rate_flag")
    private boolean rated;

    /**
     * Returns true, if this tag is a spoiler.
     */
    @NumberBasedBoolean
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
