package me.proxer.library.api.info;

import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for adding an entry to the notes, favorites or finished entries.
 *
 * @author Ruben Gees
 */
public final class ModifyUserInfoEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;
    private final UserInfoType type;

    ModifyUserInfoEndpoint(final InternalApi internalApi, final String id, final UserInfoType type) {
        this.internalApi = internalApi;
        this.id = id;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.modifyUserInfo(id, type);
    }
}
