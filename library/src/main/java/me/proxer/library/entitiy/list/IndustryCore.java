package me.proxer.library.entitiy.list;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.IndustryType;

/**
 * Entity containing the core information of an industry.
 *
 * @author Ruben Gees
 */
@Value
public class IndustryCore implements ProxerIdItem {

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
}
