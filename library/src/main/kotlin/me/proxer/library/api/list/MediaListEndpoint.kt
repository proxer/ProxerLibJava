package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.list.MediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaListSortCriteria
import me.proxer.library.enums.Medium
import me.proxer.library.enums.SortType

/**
 * Endpoint for retrieving the entries of a search as type of [MediaListEntry].
 *
 * @author Desnoo
 */
class MediaListEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<MediaListEntry>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var category: Category? = null
    private var medium: Medium? = null
    private var includeHentai: Boolean? = null
    private var sort: MediaListSortCriteria? = null
    private var sortType: SortType? = null
    private var searchStart: String? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the category to search.
     */
    fun category(category: Category?) = this.apply { this.category = category }

    /**
     * Sets the medium.
     */
    fun medium(medium: Medium?) = this.apply { this.medium = medium }

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    /**
     * Sets the criteria to search the result by.
     */
    fun sort(sort: MediaListSortCriteria?) = this.apply { this.sort = sort }

    /**
     * Sets the type to search the result by.
     */
    fun sortType(sortType: SortType?) = this.apply { this.sortType = sortType }

    /**
     * Sets the query to search for only from the start.
     */
    fun searchStart(searchStart: String?) = this.apply { this.searchStart = searchStart }

    override fun build(): ProxerCall<List<MediaListEntry>> {
        return internalApi.mediaList(category, medium, includeHentai, searchStart, sort, sortType, page, limit)
    }
}

