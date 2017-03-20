package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;

/**
 * API for the User class.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public UserApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public LoginEndpoint login(@NotNull final String username, @NotNull final String password) {
        return new LoginEndpoint(internalApi, username, password);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public LogoutEndpoint logout() {
        return new LogoutEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public UserTopTenEndpoint topTen(@Nullable final String userId, @Nullable final String username) {
        return new UserTopTenEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public UserInfoEndpoint info(@Nullable final String userId, @Nullable final String username) {
        return new UserInfoEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public UserMediaListEndpoint mediaList(@Nullable final String userId, @Nullable final String username) {
        return new UserMediaListEndpoint(internalApi, userId, username);
    }
}
