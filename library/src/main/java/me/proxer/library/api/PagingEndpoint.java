package me.proxer.library.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @NotNull
    PagingEndpoint<T> page(@Nullable Integer page);
}
