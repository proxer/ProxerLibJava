package me.proxer.library.api

/**
 * Interface for classes which implement both [PagingEndpoint] and [LimitEndpoint].
 *
 * @param <T> The type this endpoint returns.
 *
 * @author Ruben Gees
 */
interface PagingLimitEndpoint<T> : PagingEndpoint<T>, LimitEndpoint<T> {

    override fun page(page: Int?): PagingLimitEndpoint<T>
    override fun limit(limit: Int?): PagingLimitEndpoint<T>
}
