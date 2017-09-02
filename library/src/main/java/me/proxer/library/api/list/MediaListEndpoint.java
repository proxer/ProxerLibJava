package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.MediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaListSortCriteria;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.SortType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving the entries of a search as type of {@link MediaListEntry}.
 *
 * @author Desnoo
 */
@Accessors(fluent = true)
public class MediaListEndpoint implements PagingLimitEndpoint<List<MediaListEntry>> {

    private final InternalApi internalApi;

    /**
     * Sets the category to search.
     */
    @Nullable
    @Setter
    private Category category;

    /**
     * Sets the medium.
     */
    @Nullable
    @Setter
    private Medium medium;

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter
    private Boolean includeHentai;

    /**
     * Sets the criteria to search the result by.
     */
    @Nullable
    @Setter
    private MediaListSortCriteria sort;

    /**
     * Sets the type to search the result by.
     */
    @Nullable
    @Setter
    private SortType sortType;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter
    private String searchStart;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer limit;

    MediaListEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<MediaListEntry>> build() {
        return internalApi.mediaList(category, medium, includeHentai, searchStart, sort, sortType, page, limit);
    }
}

