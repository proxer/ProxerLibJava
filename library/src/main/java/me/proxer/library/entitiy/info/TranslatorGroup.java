package me.proxer.library.entitiy.info;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;
import me.proxer.library.enums.Country;
import okhttp3.HttpUrl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Entity with detailed information concerning a translator group.
 *
 * @author Ruben Gees
 */
@SuppressWarnings("JavaDoc")
@Value
public class TranslatorGroup implements ProxerIdItem, ProxerImageItem {

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
     * Returns the country, the translator group is active in.
     */
    @Getter(onMethod = @__({@Nonnull}))
    @Json(name = "country")
    private Country country;

    /**
     * {@inheritDoc}
     */
    @Getter(onMethod = @__({@Override, @Nonnull}))
    @Json(name = "image")
    private String image;

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
}
