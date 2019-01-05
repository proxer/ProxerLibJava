package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.list.MediaListEntry;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Language;
import me.proxer.library.enums.LengthBound;
import me.proxer.library.enums.MediaSearchSortCriteria;
import me.proxer.library.enums.MediaType;
import me.proxer.library.enums.TagRateFilter;
import me.proxer.library.enums.TagSpoilerFilter;
import me.proxer.library.util.ProxerUtils;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Search for all available media. Features various filter and sort options and uses paging.
 *
 * @author Desnoo
 */
@Accessors(fluent = true)
public final class MediaSearchEndpoint implements PagingLimitEndpoint<List<MediaListEntry>> {

    private static final String DELIMITER = "+";

    private final InternalApi internalApi;

    /**
     * Sets the name to search for.
     */
    @Nullable
    @Setter
    private String name;

    /**
     * Sets the language to filter by.
     */
    @Nullable
    @Setter
    private Language language;

    /**
     * Sets the type to load.
     */
    @Nullable
    @Setter
    private MediaType type;

    /**
     * Sets the criteria the list should be sorted by.
     */
    @Nullable
    @Setter
    private MediaSearchSortCriteria sort;

    /**
     * Sets the minimum/maximum episode count a entry must have to be included in the result.
     * You can specify if the count must be greater or smaller with the
     * {@link #lengthBound(LengthBound)} method.
     */
    @Nullable
    @Setter
    private Integer length;

    /**
     * To be used in conjunction with {@link #length(Integer)}. Sets if the episode count must
     * be greater or smaller than the specified value.
     */
    @Nullable
    @Setter
    private LengthBound lengthBound;

    /**
     * Sets the filter for the tags.
     */
    @Nullable
    @Setter
    private TagRateFilter tagRateFilter;

    /**
     * Sets the spoiler filter for the tags.
     */
    @Nullable
    @Setter
    private TagSpoilerFilter tagSpoilerFilter;

    /**
     * Sets the required fsk ratings.
     */
    @Nullable
    @Setter
    private EnumSet<FskConstraint> fskConstraints;

    /**
     * Sets if already finished media of the current user should be hidden from the result.
     */
    @Nullable
    @Setter
    private Boolean hideFinished;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer limit;

    @Nullable
    private String tags;

    @Nullable
    private String excludedTags;

    @Nullable
    private String genres;

    @Nullable
    private String excludedGenres;

    MediaSearchEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * Sets the tag ids a entry must have to be included in the result.
     */
    public MediaSearchEndpoint tags(@Nullable final Set<String> ids) {
        if (ids != null) {
            this.tags = ProxerUtils.join(DELIMITER, ids);
        } else {
            this.tags = null;
        }

        return this;
    }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     */
    public MediaSearchEndpoint excludedTags(@Nullable final Set<String> excludedIds) {
        if (excludedIds != null) {
            this.excludedTags = ProxerUtils.join(DELIMITER, excludedIds);
        } else {
            this.excludedTags = null;
        }

        return this;
    }

    /**
     * Sets the genre tag ids a entry must have to be included in the result.
     */
    public MediaSearchEndpoint genres(@Nullable final Set<String> ids) {
        if (ids != null) {
            this.genres = ProxerUtils.join(DELIMITER, ids);
        } else {
            this.genres = null;
        }

        return this;
    }

    /**
     * Sets the genre tag ids a entry must not have to be included in the result.
     */
    public MediaSearchEndpoint excludedGenres(@Nullable final Set<String> excludedIds) {
        if (excludedIds != null) {
            this.excludedGenres = ProxerUtils.join(DELIMITER, excludedIds);
        } else {
            this.excludedGenres = null;
        }

        return this;
    }

    @Override
    public ProxerCall<List<MediaListEntry>> build() {
        final String joinedFskConstraints = fskConstraints == null ? null
                : ProxerUtils.joinEnums(DELIMITER, fskConstraints);

        return internalApi.mediaSearch(name, language, type, joinedFskConstraints, sort, length, lengthBound,
                tags, excludedTags, genres, excludedGenres, tagRateFilter, tagSpoilerFilter,
                hideFinished == null ? null : hideFinished ? 1 : 0, page, limit);
    }
}
