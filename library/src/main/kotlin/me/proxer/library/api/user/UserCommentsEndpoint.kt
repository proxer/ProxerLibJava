package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.user.UserComment
import me.proxer.library.enums.Category
import me.proxer.library.enums.UserMediaProgress
import me.proxer.library.util.ProxerUtils

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

    private companion object {
        private const val DELIMITER = "+"
    }

    private var page: Int? = null
    private var limit: Int? = null

    private var category: Category? = null
    private var minimumLength: Int? = null
    private var states: Set<UserMediaProgress>? = null

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

    /**
     * Sets the states to filter by.
     */
    fun states(vararg states: UserMediaProgress) = this.apply {
        this.states = states.toSet()
    }

    override fun build(): ProxerCall<List<UserComment>> {
        val joinedStates = states?.joinToString(DELIMITER) { ProxerUtils.getSafeApiEnumName(it) }

        return internalApi.comments(userId, username, category, page, limit, minimumLength, joinedStates)
    }
}
