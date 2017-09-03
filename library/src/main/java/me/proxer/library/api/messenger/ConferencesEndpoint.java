package me.proxer.library.api.messenger;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.messenger.Conference;
import me.proxer.library.enums.ConferenceType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving the user's conferences.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ConferencesEndpoint implements PagingEndpoint<List<Conference>> {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer page;

    /**
     * Sets the type of conferences to load.
     */
    @Nullable
    @Setter
    private ConferenceType type;

    ConferencesEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<Conference>> build() {
        return internalApi.conferences(page, type);
    }
}
