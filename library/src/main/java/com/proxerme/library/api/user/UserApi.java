package com.proxerme.library.api.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class UserApi {

    private final InternalApi internalApi;

    public UserApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public LoginEndpoint login(@NotNull final String username, @NotNull final String password) {
        return new LoginEndpoint(internalApi, username, password);
    }

    @NotNull
    public LogoutEndpoint logout() {
        return new LogoutEndpoint(internalApi);
    }

    @NotNull
    public TopTenEndpoint topTen(@Nullable final String userId, @Nullable final String username) {
        return new TopTenEndpoint(internalApi, userId, username);
    }

    @NotNull
    public UserMediaListEndpoint mediaList(@Nullable final String userId, @Nullable final String username) {
        return new UserMediaListEndpoint(internalApi, userId, username);
    }
}
