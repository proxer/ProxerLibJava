package com.proxerme.library.entitiy.info;

import com.proxerme.library.entitiy.ProxerIdItem;
import com.proxerme.library.enums.Country;
import com.proxerme.library.enums.IndustryType;
import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Entity with detailed information concerning an industry.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class Industry implements ProxerIdItem {

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

    /**
     * Returns the link to the homepage.
     */
    @Getter(onMethod = @__({@Nullable}))
    @Json(name = "link")
    private HttpUrl link;

    /**
     * Returns the description.
     */
    @Getter(onMethod = @__({@NotNull}))
    @Json(name = "description")
    private String description;
}
