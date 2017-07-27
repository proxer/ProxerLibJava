package me.proxer.library.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface for all endpoints which allow limiting.
 *
 * @param <T> The type this endpoint returns.
 * @author Ruben Gees
 */
public interface LimitEndpoint<T> extends Endpoint<T> {

    /**
     * Sets a limit on the amount of items to return. Must be one or higher.
     */
    @Nonnull
    LimitEndpoint<T> limit(@Nullable Integer limit);
}
