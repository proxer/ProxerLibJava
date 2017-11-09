package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.info.MediaUserInfo;
import me.proxer.library.entity.info.TranslatorGroup;

/**
 * Endpoint for retrieving all information of an {@link TranslatorGroup}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class MediaUserInfoEndpoint implements Endpoint<MediaUserInfo> {

    private final InternalApi internalApi;

    private final String id;

    MediaUserInfoEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<MediaUserInfo> build() {
        return internalApi.userInfo(id);
    }
}
