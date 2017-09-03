package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.IndustryType;
import okhttp3.HttpUrl;

import javax.annotation.Nullable;

/**
 * Entity with detailed information concerning an industry.
 *
 * @author Ruben Gees
 */
@Value
public class Industry implements ProxerIdItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the type.
     */
    @Json(name = "type")
    private IndustryType type;

    /**
     * Returns the country, the industry resides in.
     */
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
    @Json(name = "description")
    private String description;
}
