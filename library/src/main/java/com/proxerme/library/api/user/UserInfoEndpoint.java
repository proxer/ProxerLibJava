package com.proxerme.library.api.user;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.UserInfo;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class UserInfoEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    UserInfoEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
                     @Nullable final String username) {
        if (userId == null && username == null) {
            throw new IllegalArgumentException("You must pass either an userId or an username.");
        }

        this.internalApi = internalApi;
        this.userId = userId;
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<UserInfo> build() {
        return internalApi.userInfo(userId, username);
    }
}
