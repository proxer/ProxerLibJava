package me.proxer.library.api;

import org.jetbrains.annotations.NotNull;

/**
 * A callback for a failed request.
 *
 * @author Ruben Gees
 */
public interface ProxerErrorCallback {

    /**
     * Called upon an error for each Request with the {@code exception}.
     */
    void onError(@NotNull ProxerException exception);
}
