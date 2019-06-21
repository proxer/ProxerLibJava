package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.user.UserComment
import me.proxer.library.enums.Category

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
class UserCommentsEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val userId: String?,
    private val username: String?
) : PagingLimitEndpoint<List<UserComment>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var category: Category? = null
    private var minimumLength: Int? = null

    init {
        require(userId.isNullOrBlank().not() || username.isNullOrBlank().not()) {
            "You must pass either an userId or an username."
        }
    }

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the category to filter by.
     */
    fun category(category: Category?) = this.apply { this.category = category }

    /**
     * Sets the minimum length to filter by.
     */
    fun minimumLength(minimumLength: Int?) = this.apply { this.minimumLength = minimumLength }

    override fun build(): ProxerCall<List<UserComment>> {
        return internalApi.comments(userId, username, category, page, limit, minimumLength)
    }
}
