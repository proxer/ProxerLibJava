package me.proxer.library.entity.ucp;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
public class UcpTopTenEntry implements ProxerIdItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "fid")
    private String id;

    /**
     * Returns the id of the associated {@link Entry}.
     */
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the medium.
     */
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the category.
     */
    @Json(name = "kat")
    private Category category;
}
