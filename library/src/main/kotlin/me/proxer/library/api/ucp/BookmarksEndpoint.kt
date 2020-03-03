package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.ucp.Bookmark
import me.proxer.library.enums.Category

/**
 * Endpoint for retrieving the bookmarks of the user.
 *
 * @author Ruben Gees
 */
class BookmarksEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<Bookmark>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var name: String? = null
    private var category: Category? = null
    private var filterAvailable: Boolean? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the name to search for.
     */
    fun name(name: String?) = this.apply { this.name = name }

    /**
     * Sets the type of category to return.
     */
    fun category(category: Category?) = this.apply { this.category = category }

    /**
     * Sets if only available or only not available bookmarks should be returned.
     *
     * This field being null means that all bookmarks are returned.
     */
    fun filterAvailable(filterAvailable: Boolean? = true) = this.apply { this.filterAvailable = filterAvailable }

    override fun build(): ProxerCall<List<Bookmark>> {
        return internalApi.bookmarks(page, limit, name, category, filterAvailable)
    }
}
