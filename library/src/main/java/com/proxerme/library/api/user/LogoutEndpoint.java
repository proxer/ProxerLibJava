package com.proxerme.library.api.user;

import com.proxerme.library.ProxerCall;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class LogoutEndpoint {

    private final InternalApi internalApi;

    public LogoutEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @NotNull
    public ProxerCall<Void> build() {
        return new ProxerCall<>(internalApi.logout());
    }
}
