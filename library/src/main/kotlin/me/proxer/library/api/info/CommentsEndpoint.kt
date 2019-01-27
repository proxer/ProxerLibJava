package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.info.Comment
import me.proxer.library.entity.info.Entry
import me.proxer.library.enums.CommentSortCriteria

/**
 * Endpoint for retrieving the comments of an [Entry].
 *
 * @author Ruben Gees
 */
class CommentsEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : PagingLimitEndpoint<List<Comment>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var sort: CommentSortCriteria? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets criteria on how to sort the comments.
     */
    fun sort(sort: CommentSortCriteria?) = this.apply { this.sort = sort }

    override fun build(): ProxerCall<List<Comment>> {
        return internalApi.comments(id, page, limit, sort)
    }
}
