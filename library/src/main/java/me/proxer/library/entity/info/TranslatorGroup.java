package me.proxer.library.entity.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;
import me.proxer.library.enums.Country;
import okhttp3.HttpUrl;

import javax.annotation.Nullable;

/**
 * Entity with detailed information concerning a translator group.
 *
 * @author Ruben Gees
 */
@Value
public class TranslatorGroup implements ProxerIdItem, ProxerImageItem {

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
     * Returns the country, the translator group is active in.
     */
    @Json(name = "country")
    private Country country;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "image")
    private String image;

    /**
     * Returns the link to the homepage.
     */
    @Nullable
    @Getter
    @Json(name = "link")
    private HttpUrl link;

    /**
     * Returns the description.
     */
    @Json(name = "description")
    private String description;

    /**
     * Returns the clicks.
     */
    @Json(name = "count")
    private int clicks;

    /**
     * Returns the amount of projects.
     */
    @Json(name = "cprojects")
    private int projectAmount;

    @SuppressWarnings("checkstyle:parameternumber")
    public TranslatorGroup(final String id, final String name, final Country country, final String image,
                           @Nullable final HttpUrl link, final String description, final int clicks,
                           final int projectAmount) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.image = image;
        this.link = link;
        this.description = description;
        this.clicks = clicks;
        this.projectAmount = projectAmount;
    }
}
