package me.proxer.library.api

/**
 * Interface for all pageable endpoints.
 *
 * @param <T> The type this endpoint returns.
 *
 * @author Ruben Gees
 */
interface PagingEndpoint<T> : Endpoint<T> {

    /**
     * Sets the page to request. Must be zero or higher.
     */
    fun page(page: Int?): PagingEndpoint<T>
}
