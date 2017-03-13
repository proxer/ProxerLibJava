package com.proxerme.library.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for all pageable endpoints.
 *
 * @author Ruben Gees
 */
public interface PagingEndpoint extends Endpoint {

    /**
     * Sets the page to request. Must be zero or higher.
     */
    @NotNull
    PagingEndpoint page(@Nullable Integer page);
}
