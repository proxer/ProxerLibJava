package me.proxer.library.api

import me.proxer.library.ProxerCall

/**
 * Interface for all endpoints.
 *
 * @param <T> The type this endpoint returns.
 *
 * @author Ruben Gees
 */
interface Endpoint<T> {

    /**
     * Builds the call for this endpoint.
     */
    fun build(): ProxerCall<T>
}
