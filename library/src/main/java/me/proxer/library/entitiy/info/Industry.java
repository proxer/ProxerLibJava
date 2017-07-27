package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.IndustryType;
import okhttp3.HttpUrl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "name")
    private String name;

    /**
     * Returns the type.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "type")
    private IndustryType type;

    /**
     * Returns the country, the industry resides in.
     */
    @Getter(onMethod = @__({@Nonnull}))
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
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "description")
    private String description;
}
