package com.proxerme.library.api.messenger;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.messenger.Conference;
import com.proxerme.library.entitiy.messenger.ConferenceInfo;
import lombok.experimental.Accessors;
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
