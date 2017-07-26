package me.proxer.library.api;

import org.jetbrains.annotations.NotNull;

/**
 * Interface for all endpoints.
 *
 * @param <T> The type this endpoint returns.
 * @author Ruben Gees
 */
public interface Endpoint<T> {

    /**
     * Builds the call for this endpoint.
     */
    @NotNull
    ProxerCall<T> build();
}
