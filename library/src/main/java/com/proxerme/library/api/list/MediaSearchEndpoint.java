package com.proxerme.library.api.list;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.MediaListEntry;
import com.proxerme.library.enums.*;
import com.proxerme.library.util.Utils;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

/**
 * Search for all available media. Features various filter and sort options and uses paging.
 *
 * @author Desnoo
 */
@Accessors(fluent = true)
public class MediaSearchEndpoint implements PagingEndpoint, LimitEndpoint {

    private static final String DELIMITER = " ";

    private final InternalApi internalApi;

    /**
     * Sets the name to search for.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private String name;

    /**
     * Sets the language to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Language language;

    /**
     * Sets the type to load.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private MediaType type;

    /**
     * Sets the criteria the list should be sorted by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private MediaSearchSortCriteria sort;

    /**
     * Sets the minimum/maximum episode count a entry must have to be included in the result.
     * You can specify if the count must be greater or smaller with the
     * {@link #lengthBound(LengthBound)} method.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Integer length;

    /**
     * To be used in conjunction with {@link #length(Integer)}. Sets if the episode count must
     * be greater or smaller than the specified value.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private LengthBound lengthBound;

    /**
     * Sets the filter for the tags.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private TagRateFilter tagRateFilter;

    /**
     * Sets the spoiler filter for the tags.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private TagSpoilerFilter tagSpoilerFilter;

    /**
     * Sets the required genres.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Set<Genre> genres;

    /**
     * Sets the excluded genres.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Set<Genre> excludedGenres;

    /**
     * Sets the required fsk ratings.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Set<FskConstraint> fskConstraints;

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

    private String tags;
    private String excludedTags;

    MediaSearchEndpoint(InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * Sets the tag ids a entry must have to be included in the result.
     */
    @NotNull
    public MediaSearchEndpoint tags(@Nullable Set<String> ids) {
        if (ids != null) {
            this.tags = Utils.join(DELIMITER, ids);
        }

        return this;
    }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     */
    @NotNull
    public MediaSearchEndpoint excludedTags(@Nullable Set<String> excludedIds) {
        if (excludedIds != null) {
            this.excludedTags = Utils.join(DELIMITER, excludedIds);
        }

        return this;
    }

    @Override
    @NotNull
    public ProxerCall<List<MediaListEntry>> build() {
        return internalApi.mediaSearch(name, language, type, genres, excludedGenres, fskConstraints,
                sort, length, lengthBound, tags, excludedTags, tagRateFilter, tagSpoilerFilter, page, limit);

    }
}