package com.proxerme.library.entitiy.list;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.enums.Country;
import com.proxerme.library.enums.IndustryType;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity containing the core information of an industry.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class IndustryCore implements ProxerIdItem {

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
     * Returns the type.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "type")
    private IndustryType type;

    /**
     * Returns the country, the industry resides in.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "country")
    private Country country;
}
