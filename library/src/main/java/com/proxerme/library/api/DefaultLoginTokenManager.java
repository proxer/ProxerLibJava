package com.proxerme.library.api;

import org.jetbrains.annotations.Nullable;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
final class DefaultLoginTokenManager implements LoginTokenManager {

    private String currentToken;

    @Override
    @Nullable
    public String provide() {
        return currentToken;
    }

    @Override
    public void persist(@Nullable final String loginToken) {
        currentToken = loginToken;
    }
}
