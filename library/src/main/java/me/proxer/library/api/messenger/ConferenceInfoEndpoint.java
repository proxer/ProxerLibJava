package me.proxer.library.api.messenger;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.messenger.Conference;
import me.proxer.library.entitiy.messenger.ConferenceInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving information concerning a {@link Conference}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ConferenceInfoEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    ConferenceInfoEndpoint(@NotNull final InternalApi internalApi, @NotNull String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<ConferenceInfo> build() {
        return internalApi.conferenceInfo(id);
    }
}
