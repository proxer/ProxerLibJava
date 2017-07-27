package me.proxer.library.entitiy.ucp;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;

import javax.annotation.Nonnull;

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
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "fid")
    private String id;

    /**
     * Returns the id of the associated {@link Entry}.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the medium.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "kat")
    private Category category;
}
