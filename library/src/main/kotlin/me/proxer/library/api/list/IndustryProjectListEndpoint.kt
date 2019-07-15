package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.list.IndustryProject
import me.proxer.library.enums.IndustryType

/**
 * Endpoint for retrieving a list of projects of an industry.
 *
 * @author Ruben Gees
 */
class IndustryProjectListEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : PagingLimitEndpoint<List<IndustryProject>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var type: IndustryType? = null
    private var includeHentai: Boolean? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the type of industries to filter by.
     */
    fun type(type: IndustryType?) = this.apply { this.type = type }

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    override fun build(): ProxerCall<List<IndustryProject>> {
        return internalApi.industryProjectList(id, type, includeHentai, page, limit)
    }
}
