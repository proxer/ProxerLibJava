package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.ucp.UcpSettings;

/**
 * Endpoint for updating the settings of the user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class SetSettingsEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final UcpSettings settings;

    SetSettingsEndpoint(final InternalApi internalApi, final UcpSettings settings) {
        this.internalApi = internalApi;
        this.settings = settings;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.setSettings(settings);
    }
}
