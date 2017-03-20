package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
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
