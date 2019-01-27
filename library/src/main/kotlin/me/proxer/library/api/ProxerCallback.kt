package me.proxer.library.api

/**
 * A callback for a successful request.
 *
 * @param <T> The type of the Result.
 * @author Ruben Gees
 */
interface ProxerCallback<T> {

    /**
     * Called upon success for each Request with the [result].
     */
    fun onSuccess(result: T?)
}
