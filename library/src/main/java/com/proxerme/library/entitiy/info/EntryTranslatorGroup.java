package com.proxerme.library.entitiy.info;

import com.proxerme.library.enums.Country;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity containing the relevant info of a translator group, associated with an {@link Entry}.
 *
 * @author Ruben Gees
 */
public class EntryTranslatorGroup implements IdItem {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "country")
    private Country country;

    private EntryTranslatorGroup() {

    }

    /**
     * The constructor.
     *
     * @param id      The id.
     * @param name    The name.
     * @param country The country this translator group is active in.
     */
    public EntryTranslatorGroup(@NotNull final String id, @NotNull final String name, @NotNull final Country country) {
        this.id = id;
        this.name = name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryTranslatorGroup translatorGroup = (EntryTranslatorGroup) o;

        if (!id.equals(translatorGroup.id)) return false;
        if (!name.equals(translatorGroup.name)) return false;
        return country.equals(translatorGroup.country);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
