package com.proxerme.library.api.info;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.enums.UserInfoType;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for adding an entry to the notes, favorites or finished entries.
 *
 * @author Ruben Gees
 */
public class ModifyUserInfoEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;
    private final UserInfoType type;

    ModifyUserInfoEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id,
                           @NotNull final UserInfoType type) {
        this.internalApi = internalApi;
        this.id = id;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Void> build() {
        return internalApi.modifyUserInfo(id, type);
    }
}
