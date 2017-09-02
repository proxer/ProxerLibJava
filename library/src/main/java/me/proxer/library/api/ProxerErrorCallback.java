package me.proxer.library.api;

/**
 * A callback for a failed request.
 *
 * @author Ruben Gees
 */
public interface ProxerErrorCallback {

    /**
     * Called upon an error for each Request with the {@code exception}.
     */
    void onError(ProxerException exception);
}
