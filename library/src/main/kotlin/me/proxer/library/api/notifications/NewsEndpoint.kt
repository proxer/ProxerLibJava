package me.proxer.library.api.notifications

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.notifications.NewsArticle

/**
 * Endpoint for retrieving news articles.
 *
 * @author Ruben Gees
 */
class NewsEndpoint internal constructor(private val internalApi: InternalApi) : PagingLimitEndpoint<List<NewsArticle>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var markAsRead: Boolean? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets if the news should be marked as read. Defaults to false.
     */
    fun markAsRead(markAsRead: Boolean?) = this.apply { this.markAsRead = markAsRead }

    override fun build(): ProxerCall<List<NewsArticle>> {
        return internalApi.news(page, limit, markAsRead)
    }
}
