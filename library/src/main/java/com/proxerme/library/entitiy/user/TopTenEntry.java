package com.proxerme.library.entitiy.user;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

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
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "eid")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "kat")
    private Category category;

    /**
     * Returns the medium.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;
}
