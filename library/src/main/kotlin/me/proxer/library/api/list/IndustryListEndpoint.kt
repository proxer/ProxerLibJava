package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.list.IndustryCore
import me.proxer.library.enums.Country

/**
 * Endpoint for retrieving a list of industries.
 *
 * @author Ruben Gees
 */
class IndustryListEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<IndustryCore>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var searchStart: String? = null
    private var search: String? = null
    private var country: Country? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the query to search for only from the start.
     */
    fun searchStart(searchStart: String?) = this.apply { this.searchStart = searchStart }

    /**
     * Sets the query to search for.
     */
    fun search(search: String?) = this.apply { this.search = search }

    /**
     * Sets the country to filter by.
     */
    fun country(country: Country?) = this.apply { this.country = country }

    override fun build(): ProxerCall<List<IndustryCore>> {
        return internalApi.industryList(searchStart, search, country, page, limit)

    }
}
