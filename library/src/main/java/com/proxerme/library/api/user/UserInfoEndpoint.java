package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.UserInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public class UserInfoEndpoint {

    private final InternalApi internalApi;

    private final String userId;
    private final String username;

    public UserInfoEndpoint(@NotNull final InternalApi internalApi, @Nullable final String userId,
                            @Nullable final String username) {
        if (userId == null && username == null) {
            throw new IllegalArgumentException("You must pass either an userId or an username.");
        }

        this.internalApi = internalApi;
        this.userId = userId;
        this.username = username;
    }

    @NotNull
    public ProxerCall<UserInfo> build() {
        return internalApi.userInfo(userId, username);
    }
}
