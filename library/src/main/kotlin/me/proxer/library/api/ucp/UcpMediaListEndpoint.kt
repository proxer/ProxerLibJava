package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.user.UserMediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.UserMediaListFilterType
import me.proxer.library.enums.UserMediaListSortCriteria

/**
 * Endpoint for requesting the media list of the current user.
 *
 * @author Ruben Gees
 */
class UcpMediaListEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<UserMediaListEntry>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var category: Category? = null
    private var search: String? = null
    private var searchStart: String? = null
    private var filter: UserMediaListFilterType? = null
    private var includeHentai: Boolean? = null
    private var sort: UserMediaListSortCriteria? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the category to filter by.
     */
    fun category(category: Category?) = this.apply { this.category = category }

    /**
     * Sets the query to search for.
     */
    fun search(search: String?) = this.apply { this.search = search }

    /**
     * Sets the query to search for only from the start.
     */
    fun searchStart(searchStart: String?) = this.apply { this.searchStart = searchStart }

    /**
     * Sets the filter.
     */
    fun filter(filter: UserMediaListFilterType?) = this.apply { this.filter = filter }

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    /**
     * Set the criteria for sorting.
     */
    fun sort(sort: UserMediaListSortCriteria?) = this.apply { this.sort = sort }

    override fun build(): ProxerCall<List<UserMediaListEntry>> {
        return internalApi.mediaList(category, page, limit, search, searchStart, filter, sort, includeHentai)
    }
}
