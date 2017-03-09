package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.Country;
import com.proxerme.library.enums.IndustryType;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity containing the relevant info of an industry, associated with an {@link Entry}.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class EntryIndustry implements IdItem {

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
     * Returns thecountry, the industry resides in.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "country")
    private Country country;
}
