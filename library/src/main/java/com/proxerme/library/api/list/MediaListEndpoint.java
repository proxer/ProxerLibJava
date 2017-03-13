package com.proxerme.library.api.list;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.MediaListEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.MediaListSortCriteria;
import com.proxerme.library.enums.MediaListSortType;
import com.proxerme.library.enums.Medium;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the entries of a search as type of {@link MediaListEntry}.
 *
 * @author Desnoo
 */
@Accessors(fluent = true)
public class MediaListEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * Sets the category to search.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Category category;

    /**
     * Sets the medium.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Medium medium;

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Boolean includeHentai;

    /**
     * Sets the criteria to search the result by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private MediaListSortCriteria sort;

    /**
     * Sets the type to search the result by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private MediaListSortType sortType;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private String searchStart;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer limit;

    MediaListEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    public ProxerCall<List<MediaListEntry>> build() {
        return internalApi.mediaList(category, medium, includeHentai, searchStart, sort, sortType, page, limit);
    }
}

