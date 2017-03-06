package com.proxerme.library.api.messenger;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.messenger.Conference;
import com.proxerme.library.enums.ConferenceType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ConferencesEndpoint {

    private final InternalApi internalApi;

    private Integer page;
    private ConferenceType type;

    ConferencesEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @NotNull
    public ConferencesEndpoint page(@Nullable final Integer page) {
        this.page = page;

        return this;
    }

    @NotNull
    public ConferencesEndpoint type(@Nullable final ConferenceType type) {
        this.type = type;

        return this;
    }

    @NotNull
    public ProxerCall<List<Conference>> build() {
        return internalApi.conferences(page, type);
    }
}
