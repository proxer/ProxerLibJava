package com.proxerme.library.api;

/**
 * Interface for all endpoints.
 *
 * @author Ruben Gees
 */
public interface Endpoint {

    /**
     * Builds the call for this endpoint.
     */
    ProxerCall build();

}
