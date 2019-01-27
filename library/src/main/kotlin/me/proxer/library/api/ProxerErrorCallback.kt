package me.proxer.library.api

/**
 * A callback for a failed request.
 *
 * @author Ruben Gees
 */
interface ProxerErrorCallback {

    /**
     * Called upon an error for each Request with the [exception].
     */
    fun onError(exception: ProxerException)
}
