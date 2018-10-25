package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.ucp.UcpSettings;

/**
 * Endpoint for retrieving the settings of the user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class SettingsEndpoint implements Endpoint<UcpSettings> {

    private final InternalApi internalApi;

    SettingsEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<UcpSettings> build() {
        return internalApi.settings();
    }
}
