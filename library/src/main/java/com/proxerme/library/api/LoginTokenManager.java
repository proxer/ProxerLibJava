package com.proxerme.library.api;

import org.jetbrains.annotations.Nullable;

/**
 * Interface for custom implementation of login token management.
 *
 * @author Ruben Gees
 */
public interface LoginTokenManager {

    /**
     * Returns the token. If no token is available, null is returned.
     */
    @Nullable
    String provide();

    /**
     * Persists the token. This could be in memory only, or on the hard disk in a file.
     */
    void persist(@Nullable String loginToken);
}
