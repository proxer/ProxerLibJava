package me.proxer.library.api;

import javax.annotation.Nullable;

/**
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
