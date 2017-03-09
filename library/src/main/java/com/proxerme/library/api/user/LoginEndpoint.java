package com.proxerme.library.api.user;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.User;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for logging an user in.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class LoginEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String username;
    private final String password;

    LoginEndpoint(@NotNull final InternalApi internalApi, @NotNull final String username,
                  @NotNull final String password) {
        this.internalApi = internalApi;
        this.username = username;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<User> build() {
        return internalApi.login(username, password);
    }
}
