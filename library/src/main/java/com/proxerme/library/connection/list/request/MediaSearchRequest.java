package com.proxerme.library.connection.list.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.list.ListRequest;
import com.proxerme.library.connection.list.entity.MediaListEntry;
import com.proxerme.library.connection.list.result.MediaListResult;
import com.proxerme.library.parameters.FskParameter.FskConstraint;
import com.proxerme.library.parameters.GenreParameter.Genre;
import com.proxerme.library.parameters.LanguageParameter.Language;
import com.proxerme.library.parameters.LengthBoundParameter.LengthBound;
import com.proxerme.library.parameters.MediaSortParameter.MediaSortCriteria;
import com.proxerme.library.parameters.TagRateFilterParameter.TagRateFilter;
import com.proxerme.library.parameters.TagSpoilerFilterParameter.TagSpoilerFilter;
import com.proxerme.library.parameters.TypeParameter.Type;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;

/**
 * Request for all available media. Features various filter and sort options (Use the withParameter
 * builders) and uses paging.
 *
 * @author Ruben Gees
 */
public class MediaSearchRequest extends ListRequest<MediaListEntry[]> {

    private static final String ENDPOINT = "entrysearch";

    private static final String NAME_PARAMETER = "name";
    private static final String LANGUAGE_PARAMETER = "language";
    private static final String TYPE_PARAMETER = "type";
    private static final String GENRES_PARAMETER = "genre";
    private static final String EXCLUDED_GENRES_PARAMETER = "nogenre";
    private static final String FSK_PARAMETER = "fsk";
    private static final String SORT_PARAMETER = "sort";
    private static final String LENGTH_PARAMETER = "length";
    private static final String LENGTH_BOUND_PARAMETER = "length-limit";
    private static final String TAGS_PARAMETER = "tags";
    private static final String EXCLUDED_TAGS_PARAMETER = "notags";
    private static final String TAG_RATE_FILTER_PARAMETER = "tagratefilter";
    private static final String TAG_SPOILER_FILTER_PARAMETER = "tagspoilerfilter";
    private static final String PAGE_PARAMETER = "p";
    private static final String LIMIT_PARAMETER = "limit";

    private static final String DELIMITER = " ";

    @Nullable
    private String name;
    @Nullable
    private String language;
    @Nullable
    private String type;
    @Nullable
    private String genres;
    @Nullable
    private String excludedGenres;
    @Nullable
    private String fskConstraints;
    @Nullable
    private String sortCriteria;
    @Nullable
    private Integer length;
    @Nullable
    private String lengthBound;
    @Nullable
    private String tags;
    @Nullable
    private String excludedTags;
    @Nullable
    private String tagRateFilter;
    @Nullable
    private String tagSpoilerFilter;
    private int page;
    @Nullable
    private Integer limit;

    /**
     * The constructor.
     *
     * @param page The page to load from.
     */
    public MediaSearchRequest(@IntRange(from = 0) int page) {
        this.page = page;
    }

    public MediaSearchRequest withName(@Nullable String name) {
        this.name = name;

        return this;
    }

    /**
     * Sets the language to filter by.
     *
     * @param language The language.
     * @return The Request.
     */
    public MediaSearchRequest withLanguage(@Nullable @Language String language) {
        this.language = language;

        return this;
    }

    /**
     * Sets the type to load.
     *
     * @param type The type.
     * @return This request.
     */
    public MediaSearchRequest withType(@Nullable @Type String type) {
        this.type = type;

        return this;
    }

    /**
     * Sets a list of genres the entry must have to be included in the result.
     *
     * @param genres The genres.
     * @return This request.
     * @see #withGenres(Iterable)
     */
    public MediaSearchRequest withGenres(@Nullable @Genre String... genres) {
        if (genres != null) {
            this.genres = TextUtils.join(DELIMITER, genres);
        }

        return this;
    }

    /**
     * Sets a list of genres the entry must have to be included in the result.
     *
     * @param genres The genres.
     * @return This request.
     * @see #withGenres(String...)
     */
    public MediaSearchRequest withGenres(@Nullable @Genre Iterable<String> genres) {
        if (genres != null) {
            this.genres = TextUtils.join(DELIMITER, genres);
        }

        return this;
    }

    /**
     * Sets the list of genres the entry must not have to be included in the result.
     *
     * @param excludedGenres The genres.
     * @return This request.
     * @see #withExcludedGenres(Iterable)
     */
    public MediaSearchRequest withExcludedGenres(@Nullable @Genre String... excludedGenres) {
        if (excludedGenres != null) {
            this.excludedGenres = TextUtils.join(DELIMITER, excludedGenres);
        }

        return this;
    }

    /**
     * Sets the list of genres the entry must not have to be included in the result.
     *
     * @param excludedGenres The genres.
     * @return This request.
     * @see #withExcludedGenres(String...)
     */
    public MediaSearchRequest withExcludedGenres(@Nullable @Genre Iterable<String> excludedGenres) {
        if (excludedGenres != null) {
            this.excludedGenres = TextUtils.join(DELIMITER, excludedGenres);
        }

        return this;
    }

    /**
     * Sets the fsk constraints the entry must meet to be included in the result.
     *
     * @param fskConstraints The constraints.
     * @return This request.
     * @see #withFskConstraints(Iterable)
     */
    public MediaSearchRequest withFskConstraints(@Nullable @FskConstraint String... fskConstraints) {
        if (fskConstraints != null) {
            this.fskConstraints = TextUtils.join(DELIMITER, fskConstraints);
        }

        return this;
    }

    /**
     * Sets the fsk constraints the entry must meet to be included in the result.
     *
     * @param fskConstraints The constraints.
     * @return This request.
     * @see #withFskConstraints(String...)
     */
    public MediaSearchRequest withFskConstraints(@Nullable @FskConstraint Iterable<String>
                                                         fskConstraints) {
        if (fskConstraints != null) {
            this.fskConstraints = TextUtils.join(DELIMITER, fskConstraints);
        }

        return this;
    }

    /**
     * Sets the criteria the list should be sorted by.
     *
     * @param sortCriteria The criteria.
     * @return This request.
     */
    public MediaSearchRequest withSortCriteria(@Nullable @MediaSortCriteria String sortCriteria) {
        this.sortCriteria = sortCriteria;

        return this;
    }

    /**
     * Sets the minimum/maximum episode count a entry must have to be included in the result.
     * You can specify if the count must be greater or smaller with the
     * {@link #withLengthBound(String)} method.
     *
     * @param length The episode count.
     * @return This Request.
     */
    public MediaSearchRequest withLength(@Nullable @IntRange(from = 0, to = 400) Integer length) {
        this.length = length;

        return this;
    }

    /**
     * To be used in conjunction with {@link #withLength(Integer)}. Sets if the epsiode count must
     * be greater or smaller than the specified value.
     *
     * @param lengthBound The bound.
     * @return This Request.
     */
    public MediaSearchRequest withLengthBound(@Nullable @LengthBound String lengthBound) {
        this.lengthBound = lengthBound;

        return this;
    }

    /**
     * Sets the tag ids a entry must have to be included in the result.
     *
     * @param tags The tag ids.
     * @return This Request.
     * @see #withTags(Iterable)
     */
    public MediaSearchRequest withTags(@Nullable String... tags) {
        if (tags != null) {
            this.tags = TextUtils.join(DELIMITER, tags);
        }

        return this;
    }

    /**
     * Sets the tag ids a entry must have to be included in the result.
     *
     * @param tags The tag ids.
     * @return This Request.
     * @see #withTags(String...)
     */
    public MediaSearchRequest withTags(@Nullable Iterable<String> tags) {
        if (tags != null) {
            this.tags = TextUtils.join(DELIMITER, tags);
        }

        return this;
    }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     *
     * @param excludedTags The tag ids.
     * @return This Request.
     * @see #withExcludedTags(Iterable)
     */
    public MediaSearchRequest withExcludedTags(@Nullable String... excludedTags) {
        if (excludedTags != null) {
            this.excludedTags = TextUtils.join(DELIMITER, excludedTags);
        }

        return this;
    }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     *
     * @param excludedTags The tag ids.
     * @return This Request.
     * @see #withExcludedTags(String...)
     */
    public MediaSearchRequest withExcludedTags(@Nullable Iterable<String> excludedTags) {
        if (excludedTags != null) {
            this.excludedTags = TextUtils.join(DELIMITER, excludedTags);
        }

        return this;
    }

    /**
     * Sets the filter for the tags.
     *
     * @param tagRateFilter The filter.
     * @return This Request.
     */
    public MediaSearchRequest withTagRateFilter(@Nullable @TagRateFilter String tagRateFilter) {
        this.tagRateFilter = tagRateFilter;

        return this;
    }

    /**
     * Sets the spoiler filter for the tags.
     *
     * @param tagSpoilerFilter The filter.
     * @return This Request.
     */
    public MediaSearchRequest withTagSpoilerFilter(@Nullable @TagSpoilerFilter String
                                                           tagSpoilerFilter) {
        this.tagSpoilerFilter = tagSpoilerFilter;

        return this;
    }

    /**
     * Sets the limit for the amount of entries returned.
     *
     * @param limit The limit.
     * @return This Request.
     */
    public MediaSearchRequest withLimit(@Nullable @IntRange(from = 1) Integer limit) {
        this.limit = limit;

        return this;
    }

    @Override
    protected ProxerResult<MediaListEntry[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(MediaListResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected String getApiEndpoint() {
        return ENDPOINT;
    }

    @NonNull
    @Override
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return Arrays.<Pair<String, ?>>asList(
                new Pair<>(NAME_PARAMETER, name),
                new Pair<>(LANGUAGE_PARAMETER, language),
                new Pair<>(TYPE_PARAMETER, type),
                new Pair<>(GENRES_PARAMETER, genres),
                new Pair<>(EXCLUDED_GENRES_PARAMETER, excludedGenres),
                new Pair<>(FSK_PARAMETER, fskConstraints),
                new Pair<>(SORT_PARAMETER, sortCriteria),
                new Pair<>(LENGTH_PARAMETER, length),
                new Pair<>(LENGTH_BOUND_PARAMETER, lengthBound),
                new Pair<>(TAGS_PARAMETER, tags),
                new Pair<>(EXCLUDED_TAGS_PARAMETER, excludedTags),
                new Pair<>(TAG_RATE_FILTER_PARAMETER, tagRateFilter),
                new Pair<>(TAG_SPOILER_FILTER_PARAMETER, tagSpoilerFilter),
                new Pair<>(PAGE_PARAMETER, page),
                new Pair<>(LIMIT_PARAMETER, limit)
        );
    }
}
