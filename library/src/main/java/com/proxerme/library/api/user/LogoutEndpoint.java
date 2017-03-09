package com.proxerme.library.api.user;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for logging out an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class LogoutEndpoint implements Endpoint {

    private final InternalApi internalApi;

    LogoutEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Void> build() {
        return internalApi.logout();
    }
}
