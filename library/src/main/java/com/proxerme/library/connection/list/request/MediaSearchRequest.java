package com.proxerme.library.connection.list.request;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.afollestad.bridge.Form;
import com.afollestad.bridge.Response;
import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.list.result.MediaSearchResult;
import com.proxerme.library.info.ProxerUrlHolder;
import com.proxerme.library.parameters.FskParameter.FskConstraint;
import com.proxerme.library.parameters.GenreParameter.Genre;
import com.proxerme.library.parameters.LanguageParameter.Language;
import com.proxerme.library.parameters.LengthBoundParameter.LengthBound;
import com.proxerme.library.parameters.MediaSortParameter.MediaSortCriteria;
import com.proxerme.library.parameters.TagRateFilterParameter.TagRateFilter;
import com.proxerme.library.parameters.TagSpoilerFilterParameter.TagSpoilerFilter;
import com.proxerme.library.parameters.TypeParameter.Type;

import static com.proxerme.library.info.ProxerTag.MEDIA_SEARCH;

/**
 * Request for all available media. Features various filter and sort options (Use the withParameter
 * builders) and uses paging.
 *
 * @author Ruben Gees
 */

public class MediaSearchRequest extends ProxerRequest<MediaSearchResult> {

    private static final String MEDIA_SEARCH_URL = "/api/v1/list/entrysearch";

    private static final String NAME_FORM = "name";
    private static final String LANGUAGE_FORM = "language";
    private static final String TYPE_FORM = "type";
    private static final String GENRES_FORM = "genre";
    private static final String EXCLUDED_GENRES_FORM = "nogenre";
    private static final String FSK_FORM = "fsk";
    private static final String SORT_FORM = "sort";
    private static final String LENGTH_FORM = "length";
    private static final String LENGTH_BOUND_FORM = "length-limit";
    private static final String TAGS_FORM = "tags";
    private static final String EXCLUDED_TAGS_FORM = "notags";
    private static final String TAG_RATE_FILTER_FORM = "tagratefilter";
    private static final String TAG_SPOILER_FILTER_FORM = "tagspoilerfilter";
    private static final String PAGE_FORM = "p";
    private static final String LIMIT_FORM = "limit";

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
            this.genres = TextUtils.join(" ", genres);
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
            this.genres = TextUtils.join(" ", genres);
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
            this.excludedGenres = TextUtils.join(" ", excludedGenres);
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
            this.excludedGenres = TextUtils.join(" ", excludedGenres);
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
            this.fskConstraints = TextUtils.join(" ", fskConstraints);
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
            this.fskConstraints = TextUtils.join(" ", fskConstraints);
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
            this.tags = TextUtils.join(" ", tags);
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
            this.tags = TextUtils.join(" ", tags);
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
            this.excludedTags = TextUtils.join(" ", excludedTags);
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
            this.excludedTags = TextUtils.join(" ", excludedTags);
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
    protected int getTag() {
        return MEDIA_SEARCH;
    }

    @Override
    protected MediaSearchResult parse(@NonNull Response response) throws Exception {
        return response.asClass(MediaSearchResult.class);
    }

    @NonNull
    @Override
    protected String getURL() {
        return ProxerUrlHolder.getHost() + MEDIA_SEARCH_URL;
    }

    @Override
    protected Form getBody() {
        Form form = new Form();

        if (name != null) {
            form.add(NAME_FORM, name);
        }

        if (language != null) {
            form.add(LANGUAGE_FORM, language);
        }

        if (type != null) {
            form.add(TYPE_FORM, type);
        }

        if (genres != null) {
            form.add(GENRES_FORM, genres);
        }

        if (excludedGenres != null) {
            form.add(EXCLUDED_GENRES_FORM, excludedGenres);
        }

        if (fskConstraints != null) {
            form.add(FSK_FORM, fskConstraints);
        }

        if (sortCriteria != null) {
            form.add(SORT_FORM, sortCriteria);
        }

        if (length != null) {
            form.add(LENGTH_FORM, length);
        }

        if (lengthBound != null) {
            form.add(LENGTH_BOUND_FORM, lengthBound);
        }

        if (tags != null) {
            form.add(TAGS_FORM, tags);
        }

        if (excludedTags != null) {
            form.add(EXCLUDED_TAGS_FORM, excludedTags);
        }

        if (tagRateFilter != null) {
            form.add(TAG_RATE_FILTER_FORM, tagRateFilter);
        }

        if (tagSpoilerFilter != null) {
            form.add(TAG_SPOILER_FILTER_FORM, tagSpoilerFilter);
        }

        form.add(PAGE_FORM, page);

        if (limit != null) {
            form.add(LIMIT_FORM, limit);
        }

        return form;
    }
}
