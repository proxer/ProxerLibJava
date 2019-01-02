package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Entity representing a single genre in the context of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class InfoGenre implements ProxerIdItem, ProxerDateItem {

    /**
     * Returns the id of the connection between this genre and the entry.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the id of the associated tag entry.
     */
    @Json(name = "tid")
    private String entryTagId;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp")
    private Date date;

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
