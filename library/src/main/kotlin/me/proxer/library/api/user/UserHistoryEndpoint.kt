package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.user.UserHistoryEntry

/**
 * Endpoint for requesting the history of the current user.
 *
 * @author Ruben Gees
 */
class UserHistoryEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val userId: String?,
    private val username: String?
) : PagingLimitEndpoint<List<UserHistoryEntry>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var includeHentai: Boolean? = null

    init {
        require(userId.isNullOrBlank().not() || username.isNullOrBlank().not()) {
            "You must pass either an userId or an username."
        }
    }

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    override fun build(): ProxerCall<List<UserHistoryEntry>> {
        return internalApi.history(userId, username, page, limit, includeHentai)
    }
}
