package com.proxerme.library.api;

import org.jetbrains.annotations.NotNull;

/**
 * Interface for all endpoints.
 *
 * @author Ruben Gees
 */
public interface Endpoint {

    /**
     * Builds the call for this endpoint.
     */
    @NotNull
    ProxerCall build();
}
