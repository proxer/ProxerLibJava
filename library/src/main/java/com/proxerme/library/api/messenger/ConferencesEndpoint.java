package com.proxerme.library.api.messenger;

import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.messenger.Conference;
import com.proxerme.library.enums.ConferenceType;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the user's conferences.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ConferencesEndpoint implements PagingEndpoint {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer page;

    /**
     * Sets the type of conferences to load.
     */
    @Setter(onMethod = @__({@Nullable}))
    private ConferenceType type;

    ConferencesEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Conference>> build() {
        return internalApi.conferences(page, type);
    }
}
