package me.proxer.library.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface for all pageable endpoints.
 *
 * @param <T> The type this endpoint returns.
 * @author Ruben Gees
 */
public interface PagingEndpoint<T> extends Endpoint<T> {

    /**
     * Sets the page to request. Must be zero or higher.
     */
    @Nonnull
    PagingEndpoint<T> page(@Nullable Integer page);
}
