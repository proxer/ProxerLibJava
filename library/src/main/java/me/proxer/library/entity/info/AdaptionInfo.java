package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Medium;

import javax.annotation.Nullable;

@Value
@EqualsAndHashCode(onParam = @__({@Nullable}))
public class AdaptionInfo implements ProxerIdItem {

    /**
     * Returns the id of this comment.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the medium
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the medium
     */
    @Json(name = "medium")
    private Medium medium;
}
