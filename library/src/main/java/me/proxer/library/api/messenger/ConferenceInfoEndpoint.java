package me.proxer.library.api.messenger;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.messenger.Conference;
import me.proxer.library.entitiy.messenger.ConferenceInfo;

import javax.annotation.Nonnull;

/**
 * Endpoint for retrieving information concerning a {@link Conference}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ConferenceInfoEndpoint implements Endpoint<ConferenceInfo> {

    private final InternalApi internalApi;

    private final String id;

    ConferenceInfoEndpoint(@Nonnull final InternalApi internalApi, @Nonnull String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<ConferenceInfo> build() {
        return internalApi.conferenceInfo(id);
    }
}
