package me.proxer.library.api.info;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.entitiy.info.Relation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Boolean includeHentai;

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
        return internalApi.relations(id, includeHentai);
    }
}
