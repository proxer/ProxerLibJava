package com.proxerme.library.entitiy.info;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.enums.Country;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import org.jetbrains.annotations.NotNull;

/**
 * Entity containing the relevant info of a translator group, associated with an {@link Entry}.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class EntryTranslatorGroup implements ProxerIdItem {

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
}
