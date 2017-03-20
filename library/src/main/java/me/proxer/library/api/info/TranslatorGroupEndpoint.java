package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.TranslatorGroup;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving all information of an {@link TranslatorGroup}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    TranslatorGroupEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<TranslatorGroup> build() {
        return internalApi.translatorGroup(id);
    }
}
