package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import retrofit2.Retrofit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    public UserApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public LoginEndpoint login(@Nonnull final String username, @Nonnull final String password) {
        return new LoginEndpoint(internalApi, username, password);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public LogoutEndpoint logout() {
        return new LogoutEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public UserTopTenEndpoint topTen(@Nullable final String userId, @Nullable final String username) {
        return new UserTopTenEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public UserInfoEndpoint info(@Nullable final String userId, @Nullable final String username) {
        return new UserInfoEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public UserMediaListEndpoint mediaList(@Nullable final String userId, @Nullable final String username) {
        return new UserMediaListEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public UserCommentsEndpoint comments(@Nullable final String userId, @Nullable final String username) {
        return new UserCommentsEndpoint(internalApi, userId, username);
    }
}
