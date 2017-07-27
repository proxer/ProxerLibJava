package me.proxer.library.api;

import javax.annotation.Nonnull;

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
    @Nonnull
    ProxerCall<T> build();
}
