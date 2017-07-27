package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Endpoint for logging an user in.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class LoginEndpoint implements Endpoint<User> {

    private final InternalApi internalApi;

    private final String username;
    private final String password;

    /**
     * Sets he secret key for authentication with 2FA.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
    private String secretKey;

    LoginEndpoint(@Nonnull final InternalApi internalApi, @Nonnull final String username,
                  @Nonnull final String password) {
        this.internalApi = internalApi;
        this.username = username;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<User> build() {
        return internalApi.login(username, password, secretKey);
    }
}
