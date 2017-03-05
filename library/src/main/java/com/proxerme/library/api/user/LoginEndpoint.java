package com.proxerme.library.api.user;

import com.proxerme.library.ProxerCall;
import com.proxerme.library.entitiy.user.User;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class LoginEndpoint {

    private final InternalApi internalApi;

    private String username;
    private String password;

    public LoginEndpoint(@NotNull final InternalApi internalApi, @NotNull final String username,
                         @NotNull final String password) {
        this.internalApi = internalApi;
        this.username = username;
        this.password = password;
    }

    @NotNull
    public ProxerCall<User> build() {
        return new ProxerCall<>(internalApi.login(username, password));
    }
}
