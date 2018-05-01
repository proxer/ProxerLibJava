package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import retrofit2.Retrofit;

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
    public UserApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public LoginEndpoint login(final String username, final String password) {
        return new LoginEndpoint(internalApi, username, password);
    }

    /**
     * Returns the respective endpoint.
     */
    public LogoutEndpoint logout() {
        return new LogoutEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public UserTopTenEndpoint topTen(@Nullable final String userId, @Nullable final String username) {
        return new UserTopTenEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    public UserInfoEndpoint info(@Nullable final String userId, @Nullable final String username) {
        return new UserInfoEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    public UserAboutEndpoint about(@Nullable final String userId, @Nullable final String username) {
        return new UserAboutEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    public UserMediaListEndpoint mediaList(@Nullable final String userId, @Nullable final String username) {
        return new UserMediaListEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    public UserCommentsEndpoint comments(@Nullable final String userId, @Nullable final String username) {
        return new UserCommentsEndpoint(internalApi, userId, username);
    }

    /**
     * Returns the respective endpoint.
     */
    public UserHistoryEndpoint history(@Nullable final String userId, @Nullable final String username) {
        return new UserHistoryEndpoint(internalApi, userId, username);
    }
}
