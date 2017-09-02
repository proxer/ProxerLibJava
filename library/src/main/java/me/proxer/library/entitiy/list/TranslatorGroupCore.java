package me.proxer.library.entitiy.list;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;
import me.proxer.library.enums.Country;

/**
 * Entity containing the core information of a translator group.
 *
 * @author Ruben Gees
 */
@Value
public class TranslatorGroupCore implements ProxerIdItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Json(name = "id")
    private String id;

    /**
     * Returns the name.
     */
    @Json(name = "name")
    private String name;

    /**
     * Returns the country, the translator group is active in.
     */
    @Json(name = "country")
    private Country country;

    /**
     * {@inheritDoc}
     */
    @Json(name = "image")
    private String image;
}
