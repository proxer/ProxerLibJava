package com.proxerme.library.entitiy.list;

import com.proxerme.library.entitiy.interfaces.IdItem;
import com.proxerme.library.entitiy.interfaces.ImageItem;
import com.proxerme.library.enums.Country;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity containing the core information of a translator group.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class TranslatorGroupCore implements IdItem, ImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the country, the translator group is active in.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "country")
    private Country country;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @NotNull}))
    @Json(name = "image")
    private String imageId;
}
