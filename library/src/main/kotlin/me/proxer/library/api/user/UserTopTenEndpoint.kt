package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.user.TopTenEntry
import me.proxer.library.enums.Category

/**
 * Endpoint for requesting an user's top ten list.
 *
 * @author Ruben Gees
 */
class UserTopTenEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val userId: String?,
    private val username: String?
) : Endpoint<List<TopTenEntry>> {

    private var category: Category? = null
    private var includeHentai: Boolean? = null

    init {
        require(userId.isNullOrBlank().not() || username.isNullOrBlank().not()) {
            "You must pass either an userId or an username."
        }
    }

    /**
     * Sets the category to filter by.
     */
    fun category(category: Category?) = this.apply { this.category = category }

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    override fun build(): ProxerCall<List<TopTenEntry>> {
        return internalApi.topTen(userId, username, category, includeHentai)
    }
}
