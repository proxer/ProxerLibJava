package com.proxerme.library.api;

import org.jetbrains.annotations.NotNull;

/**
 * A callback for an unsuccessful request.
 *
 * @author Ruben Gees
 */
public interface ProxerErrorCallback {

    /**
     * Called upon an error for each Request with the Exception.
     *
     * @param exception The Exception.
     */
    void onError(@NotNull ProxerException exception);
}
