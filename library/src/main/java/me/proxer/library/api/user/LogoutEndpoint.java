package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for logging out an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class LogoutEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    LogoutEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.logout();
    }
}
