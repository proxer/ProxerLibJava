package com.proxerme.library.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Interface for all endpoints which allow limiting.
 *
 * @author Ruben Gees
 */
public interface LimitEndpoint extends Endpoint {

    /**
     * Sets a limit on the amount of items to return. Must be one or higher
     */
    @NotNull
    LimitEndpoint limit(@Nullable Integer limit);
}
