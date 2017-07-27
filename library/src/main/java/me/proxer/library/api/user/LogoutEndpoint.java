package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

import javax.annotation.Nonnull;

/**
 * Endpoint for logging out an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class LogoutEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    LogoutEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<Void> build() {
        return internalApi.logout();
    }
}
