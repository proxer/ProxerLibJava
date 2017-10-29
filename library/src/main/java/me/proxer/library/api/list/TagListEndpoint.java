package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.list.Tag;
import me.proxer.library.enums.TagSortCriteria;
import me.proxer.library.enums.TagSubType;
import me.proxer.library.enums.TagType;

import javax.annotation.Nullable;
import java.util.List;

@Accessors(fluent = true)
public final class TagListEndpoint implements Endpoint<List<Tag>> {

    private final InternalApi internalApi;
    /**
     * Sets the name to search for.
     */
    @Nullable
    @Setter
    private String name;
    /**
     * Sets the type to filter by.
     */
    @Nullable
    @Setter
    private TagType type;
    /**
     * Sets the criteria to sort by.
     */
    @Nullable
    @Setter
    private TagSortCriteria sortCriteria;
    /**
     * Sets the sub type to filter by.
     */
    @Nullable
    @Setter
    private TagSubType subType;
    @Nullable
    private TagSortType sortType;

    TagListEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * Sets the sort type to be ascending.
     */
    public TagListEndpoint sortAscending() {
        sortType = TagSortType.ASCENDING;

        return this;
    }

    /**
     * Sets the sort type to be ascending.
     */
    public TagListEndpoint sortDescending() {
        sortType = TagSortType.DESCENDING;

        return this;
    }

    @Override
    public ProxerCall<List<Tag>> build() {
        return internalApi.tagList(name, type, sortCriteria, sortType, subType);
    }
}
