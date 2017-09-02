package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.User;

import javax.annotation.Nullable;

/**
 * Endpoint for logging an user in.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class LoginEndpoint implements Endpoint<User> {

    private final InternalApi internalApi;

    private final String username;
    private final String password;

    /**
     * Sets the secret key for authentication with 2FA.
     */
    @Nullable
    @Setter
    private String secretKey;

    LoginEndpoint(final InternalApi internalApi, final String username, final String password) {
        this.internalApi = internalApi;
        this.username = username;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<User> build() {
        return internalApi.login(username, password, secretKey);
    }
}
