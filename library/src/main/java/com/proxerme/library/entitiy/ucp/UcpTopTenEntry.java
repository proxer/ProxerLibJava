package com.proxerme.library.entitiy.ucp;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity representing a single entry in the history list.
 *
 * @author Ruben Gees
 */
@Value
public class UcpTopTenEntry implements IdItem {

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "fid")
    private String id;

    /**
     * Returns the id of the associated {@link com.proxerme.library.entitiy.info.Entry}.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "eid")
    private String entryId;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the medium.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "medium")
    private Medium medium;

    /**
     * Returns the category.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "kat")
    private Category category;
}
