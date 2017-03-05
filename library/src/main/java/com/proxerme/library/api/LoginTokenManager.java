package com.proxerme.library.api;

import org.jetbrains.annotations.Nullable;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public interface LoginTokenManager {

    @Nullable
    String provide();

    void persist(@Nullable String loginToken);
}
