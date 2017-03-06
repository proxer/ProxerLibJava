package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.User;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class LoginEndpoint {

    private final InternalApi internalApi;

    private final String username;
    private final String password;

    LoginEndpoint(@NotNull final InternalApi internalApi, @NotNull final String username,
                  @NotNull final String password) {
        this.internalApi = internalApi;
        this.username = username;
        this.password = password;
    }

    @NotNull
    public ProxerCall<User> build() {
        return internalApi.login(username, password);
    }
}
