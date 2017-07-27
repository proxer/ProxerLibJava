package me.proxer.library.entitiy.user;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Medium;

import javax.annotation.Nonnull;

/**
 * Entity representing a single Topten entry.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class TopTenEntry implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "eid")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the medium.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "medium")
    private Medium medium;
}
