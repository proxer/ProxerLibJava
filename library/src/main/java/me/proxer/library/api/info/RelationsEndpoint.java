package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.entitiy.info.Relation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Endpoint for retrieving the relations of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class RelationsEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;

    RelationsEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<Relation>> build() {
        return internalApi.relations(id);
    }
}
