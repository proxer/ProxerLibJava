package com.proxerme.library.connection;

/**
 * A callback for an unsuccessful request.
 */
public interface ProxerErrorCallback {

    /**
     * Called upon an error for each Request with the Exception.
     *
     * @param exception The Exception.
     */
    void onError(ProxerException exception);
}
