package me.proxer.library.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @NotNull
    LimitEndpoint<T> limit(@Nullable Integer limit);
}
