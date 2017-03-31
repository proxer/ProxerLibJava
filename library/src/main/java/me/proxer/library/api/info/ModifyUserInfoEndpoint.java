package me.proxer.library.api.info;

import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
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
