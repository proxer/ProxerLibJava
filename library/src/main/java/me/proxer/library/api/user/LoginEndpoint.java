package me.proxer.library.api.user;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @Setter(onMethod = @__({@NotNull}))
    private String secretKey;

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
        return internalApi.login(username, password, secretKey);
    }
}
