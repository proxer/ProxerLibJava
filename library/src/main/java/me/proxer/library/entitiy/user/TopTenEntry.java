package me.proxer.library.entitiy.user;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;

/**
 * Entity representing a single Topten entry.
 *
 * @author Ruben Gees
 */
@Value
public class TopTenEntry implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "eid")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the category.
     */
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the medium.
     */
    @Json(name = "medium")
    private Medium medium;
}
