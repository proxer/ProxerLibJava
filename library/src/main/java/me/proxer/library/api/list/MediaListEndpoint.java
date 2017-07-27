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

import javax.annotation.Nonnull;
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
    @Setter(onMethod = @__({@Nonnull}))
    private Category category;

    /**
     * Sets the medium.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Medium medium;

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private Boolean includeHentai;

    /**
     * Sets the criteria to search the result by.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private MediaListSortCriteria sort;

    /**
     * Sets the type to search the result by.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private SortType sortType;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private String searchStart;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer limit;

    MediaListEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<List<MediaListEntry>> build() {
        return internalApi.mediaList(category, medium, includeHentai, searchStart, sort, sortType, page, limit);
    }
}

