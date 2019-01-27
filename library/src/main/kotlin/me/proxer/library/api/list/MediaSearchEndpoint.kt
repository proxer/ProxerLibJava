package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.list.MediaListEntry
import me.proxer.library.enums.FskConstraint
import me.proxer.library.enums.Language
import me.proxer.library.enums.LengthBound
import me.proxer.library.enums.MediaSearchSortCriteria
import me.proxer.library.enums.MediaType
import me.proxer.library.enums.TagRateFilter
import me.proxer.library.enums.TagSpoilerFilter
import me.proxer.library.internal.util.toIntOrNull
import me.proxer.library.util.ProxerUtils

/**
 * Search for all available media. Features various filter and sort options and uses paging.
 *
 * @author Desnoo
 */
class MediaSearchEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<MediaListEntry>> {

    private companion object {
        private const val DELIMITER = "+"
    }

    private var page: Int? = null
    private var limit: Int? = null

    private var name: String? = null
    private var language: Language? = null
    private var type: MediaType? = null
    private var sort: MediaSearchSortCriteria? = null
    private var length: Int? = null
    private var lengthBound: LengthBound? = null
    private var tagRateFilter: TagRateFilter? = null
    private var tagSpoilerFilter: TagSpoilerFilter? = null
    private var fskConstraints: Set<FskConstraint>? = null
    private var hideFinished: Boolean? = null
    private var tags: Set<String>? = null
    private var excludedTags: Set<String>? = null
    private var genres: Set<String>? = null
    private var excludedGenres: Set<String>? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the name to search for.
     */
    fun name(name: String?) = this.apply { this.name = name }

    /**
     * Sets the language to filter by.
     */
    fun language(language: Language?) = this.apply { this.language = language }

    /**
     * Sets the type to load.
     */
    fun type(type: MediaType?) = this.apply { this.type = type }

    /**
     * Sets the criteria the list should be sorted by.
     */
    fun sort(sort: MediaSearchSortCriteria?) = this.apply { this.sort = sort }

    /**
     * Sets the minimum/maximum episode count a entry must have to be included in the result.
     * You can specify if the count must be greater or smaller with the [lengthBound] method.
     */
    fun length(length: Int?) = this.apply { this.length = length }

    /**
     * To be used in conjunction with [length]. Sets if the episode count must
     * be greater or smaller than the specified value.
     */
    fun lengthBound(lengthBound: LengthBound?) = this.apply { this.lengthBound = lengthBound }

    /**
     * Sets the filter for the tags.
     */
    fun tagRateFilter(tagRateFilter: TagRateFilter?) = this.apply { this.tagRateFilter = tagRateFilter }

    /**
     * Sets the spoiler filter for the tags.
     */
    fun tagSpoilerFilter(tagSpoilerFilter: TagSpoilerFilter?) = this.apply { this.tagSpoilerFilter = tagSpoilerFilter }

    /**
     * Sets the required fsk ratings.
     */
    fun fskConstraints(fskConstraints: Set<FskConstraint>?) = this.apply { this.fskConstraints = fskConstraints }

    /**
     * Sets if already finished media of the current user should be hidden from the result.
     */
    fun hideFinished(hideFinished: Boolean?) = this.apply { this.hideFinished = hideFinished }

    /**
     * Sets the tag ids a entry must have to be included in the result.
     */
    fun tags(ids: Set<String>?) = this.apply { this.tags = ids }

    /**
     * Sets the tag ids a entry must not have to be included in the result.
     */
    fun excludedTags(excludedIds: Set<String>?) = this.apply { this.excludedTags = excludedIds }

    /**
     * Sets the genre tag ids a entry must have to be included in the result.
     */
    fun genres(ids: Set<String>?) = this.apply { this.genres = ids }

    /**
     * Sets the genre tag ids a entry must not have to be included in the result.
     */
    fun excludedGenres(excludedIds: Set<String>?) = this.apply { this.excludedGenres = excludedIds }

    override fun build(): ProxerCall<List<MediaListEntry>> {
        val joinedFskConstraints = fskConstraints
            ?.map { ProxerUtils.getSafeApiEnumName(it) }
            ?.joinToString(DELIMITER)

        val joinedTags = tags?.joinToString(DELIMITER)
        val joinedExcludedTags = excludedTags?.joinToString(DELIMITER)
        val joinedGenres = genres?.joinToString(DELIMITER)
        val joinedExcludedGenres = excludedGenres?.joinToString(DELIMITER)

        return internalApi.mediaSearch(
            name, language, type, joinedFskConstraints, sort, length, lengthBound,
            joinedTags, joinedExcludedTags, joinedGenres, joinedExcludedGenres,
            tagRateFilter, tagSpoilerFilter, hideFinished.toIntOrNull(), page, limit
        )
    }
}
