package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.Country;
import com.proxerme.library.enums.IndustryType;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity containing the relevant info of an industry, associated with an {@link Entry}.
 *
 * @author Ruben Gees
 */
public class EntryIndustry implements IdItem {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "type")
    private IndustryType type;
    @Json(name = "country")
    private Country country;

    private EntryIndustry() {
    }

    /**
     * The constructor.
     *
     * @param id      The id.
     * @param name    The name.
     * @param type    The type of the industry.
     * @param country The country the industry resides in.
     */
    public EntryIndustry(@NotNull final String id, @NotNull final String name, @NotNull final IndustryType type,
                         @NotNull final Country country) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.country = country;
    }

    /**
     * Returns the id.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the name.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the type.
     *
     * @return The type.
     */
    @NotNull
    public IndustryType getType() {
        return type;
    }

    /**
     * Returns the country.
     *
     * @return The country.
     */
    @NotNull
    public Country getCountry() {
        return country;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final EntryIndustry that = (EntryIndustry) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (type != that.type) return false;
        return country == that.country;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
