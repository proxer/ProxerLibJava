package com.proxerme.library.entitiy.user;

import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import com.proxerme.library.interfaces.IdItem;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;

/**
 * Entity representing a single Topten entry.
 *
 * @author Ruben Gees
 */
public class TopTenEntry implements IdItem {

    @Json(name = "eid")
    private String id;
    @Json(name = "name")
    private String name;
    @Json(name = "kat")
    private Category category;
    @Json(name = "medium")
    private Medium medium;

    private TopTenEntry() {

    }

    /**
     * The constructor.
     *
     * @param id       The id of the entry.
     * @param name     The name of the entry.
     * @param category The category.
     * @param medium   The medium.
     */
    public TopTenEntry(@NotNull String id, @NotNull String name, @NotNull Category category, @NotNull Medium medium) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.medium = medium;
    }

    /**
     * Returns the id of this entry.
     *
     * @return The id.
     */
    @Override
    @NotNull
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this entry.
     *
     * @return The name.
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * Returns the category of this entry.
     *
     * @return The category.
     */
    @NotNull
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the medium of this entry.
     *
     * @return The medium.
     */
    @NotNull
    public Medium getMedium() {
        return medium;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopTenEntry that = (TopTenEntry) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (category != that.category) return false;
        return medium == that.medium;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + medium.hashCode();
        return result;
    }
}
