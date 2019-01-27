package me.proxer.library.api

/**
 * Interface for all endpoints which allow limiting.
 *
 * @param <T> The type this endpoint returns.
 *
 * @author Ruben Gees
 */
interface LimitEndpoint<T> : Endpoint<T> {

    /**
     * Sets a limit on the amount of items to return. Must be one or higher.
     */
    fun limit(limit: Int?): LimitEndpoint<T>
}
