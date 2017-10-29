package me.proxer.library.entity.list;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.TagSubType;
import me.proxer.library.enums.TagType;

import javax.annotation.Nullable;

/**
 * Entity representing a single tag.
 *
 * @author Ruben Gees
 */
@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class Tag implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the type.
     */
    @Json(name = "type")
    private TagType type;

    /**
     * Returns the name.
     */
    @Json(name = "tag")
    private String name;

    /**
     * Returns the name.
     */
    @Json(name = "description")
    private String description;

    /**
     * Returns the type.
     */
    @Json(name = "subtype")
    private TagSubType subType;

    /**
     * Returns the type.
     */
    @Json(name = "is_spoiler")
    private boolean isSpoiler;
}
