package me.proxer.library.api.user;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.user.UserInfo;

import javax.annotation.Nullable;

/**
 * Endpoint for requesting various information of an user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class UserInfoEndpoint implements Endpoint<UserInfo> {

    private final InternalApi internalApi;

    @Nullable
    private final String userId;

    @Nullable
    private final String username;

    UserInfoEndpoint(final InternalApi internalApi, @Nullable final String userId,
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
    public ProxerCall<UserInfo> build() {
        return internalApi.userInfo(userId, username);
    }
}
