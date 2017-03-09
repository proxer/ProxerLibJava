package com.proxerme.library.api;

import org.jetbrains.annotations.Nullable;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public interface PagingEndpoint extends Endpoint {

    /**
     * Sets the page to request. Must be zero or higher.
     */
    PagingEndpoint page(@Nullable Integer page);

}
