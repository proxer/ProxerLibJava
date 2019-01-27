package me.proxer.library.api.forum

import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.forum.Topic

/**
 * Endpoint for retrieving a forum topic with its posts.
 *
 * @author Ruben Gees
 */
class TopicEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : PagingLimitEndpoint<Topic> {

    private var page: Int? = null
    private var limit: Int? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    override fun build(): ProxerCall<Topic> {
        return internalApi.topic(id, page, limit)
    }
}
