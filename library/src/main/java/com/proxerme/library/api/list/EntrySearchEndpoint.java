package com.proxerme.library.api.list;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.MediaEntry;
import com.proxerme.library.enums.Language;
import com.proxerme.library.enums.MediaType;
import com.proxerme.library.util.Utils;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Search for all available media. Features various filter, sort options and uses paging.
 *
 * @author Desnoo
 */
@Accessors(fluent = true)
public class EntrySearchEndpoint implements PagingEndpoint, LimitEndpoint {

    private static final String DELIMITER = " ";

    private InternalApi internalApi;

    @Setter(onMethod = @__({@Nullable}))
    private String name;

    /**
     * Sets the language to filter by.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Language language;

    /**
     * Sets the type to load.
     */
    @Setter(onMethod = @__({@Nullable}))
    private MediaType type;

    /**
     * Sets the criteria the list should be sorted by.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String sortCriteria;

    /**
     * Sets the minimum/maximum episode count a entry must have to be included in the result.
     * You can specify if the count must be greater or smaller with the
     * {@link #lengthBound(String)} method.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Integer length;

    /**
     * To be used in conjunction with {@link #length(Integer)}. Sets if the episode count must
     * be greater or smaller than the specified value.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String lengthBound;

    /**
     * Sets the filter for the tags.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String tagRateFilter;

    /**
     * Sets the spoiler filter for the tags.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String tagSpoilerFilter;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer limit;

    private String tags;
    private String excludedTags;
    private String genres;
    private String excludedGenres;
    private String fskConstraints;

    /**
     * The constructor.
     */
    public EntrySearchEndpoint(InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * Sets a list of genres the entry must have to be included in the result.
     */
    public EntrySearchEndpoint genres(@Nullable String... genres) {
        if (genres != null) {
            this.genres = Utils.join(DELIMITER, genres);
        }
        return this;
    }

    /**
     * Sets a list of genres the entry must have to be included in the result.
     */
    public EntrySearchEndpoint genres(@Nullable Iterable<String> genres) {
        if (genres != null) {
            this.genres = Utils.join(DELIMITER, genres);
        }
        return this;
    }

    /**
     * Sets the list of genres the entry must not have to be included in the result.
     */
    public EntrySearchEndpoint excludedGenres(@Nullable String... excludedGenres) {
        if (excludedGenres != null) {
            this.excludedGenres = Utils.join(DELIMITER, excludedGenres);
        }
        return this;
    }

    /**
     * Sets the list of genres the entry must not have to be included in the result.
     */
    public EntrySearchEndpoint excludedGenres(@Nullable Iterable<String> excludedGenres) {
        if (excludedGenres != null) {
            this.excludedGenres = Utils.join(DELIMITER, excludedGenres);
        }
        return this;
    }

    /**
     * Sets the fsk constraints the entry must meet to be included in the result.
     */
    public EntrySearchEndpoint fskConstraints(@Nullable String... fskConstraints) {
        if (fskConstraints != null) {
            this.fskConstraints = Utils.join(DELIMITER, fskConstraints);
        }
        return this;
    }

    /**
     * Sets the fsk constraints the entry must meet to be included in the result.
     */
    public EntrySearchEndpoint fskConstraints(@Nullable Iterable<String> fskConstraints) {
        if (fskConstraints != null) {
            this.fskConstraints = Utils.join(DELIMITER, fskConstraints);
        }
        return this;
    }


    /**
     * Sets the tag ids a entry must have to be included in the result.
     */
    public EntrySearchEndpoint tags(@Nullable String... tags) {
        if (tags != null) {
            this.tags = Utils.join(DELIMITER, tags);
        }
        return this;
    }

    /**
     * Sets the tag ids a entry must have to be included in the result.
     */
    public EntrySearchEndpoint tags(@Nullable Iterable<String> tags) {
        if (tags != null) {
            this.tags = Utils.join(DELIMITER, tags);
        }
        return this;
    }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     */
    public EntrySearchEndpoint excludedTags(@Nullable String... excludedTags) {
        if (excludedTags != null) {
            this.excludedTags = Utils.join(DELIMITER, excludedTags);
        }
        return this;
    }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     */
    public EntrySearchEndpoint excludedTags(@Nullable Iterable<String> excludedTags) {
        if (excludedTags != null) {
            this.excludedTags = Utils.join(DELIMITER, excludedTags);
        }
        return this;
    }

    @Override
    public ProxerCall<List<MediaEntry>> build() {
        return internalApi.searchEntries(name, language, type, genres, excludedGenres, fskConstraints,
                sortCriteria, length, lengthBound, tags, excludedTags, tagRateFilter, tagSpoilerFilter, page, limit);

    }
}
