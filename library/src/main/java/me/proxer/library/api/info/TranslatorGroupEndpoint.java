package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.TranslatorGroup;

/**
 * Endpoint for retrieving all information of an {@link TranslatorGroup}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupEndpoint implements Endpoint<TranslatorGroup> {

    private final InternalApi internalApi;

    private final String id;

    TranslatorGroupEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<TranslatorGroup> build() {
        return internalApi.translatorGroup(id);
    }
}
