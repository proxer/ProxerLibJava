package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for deleting a bookmark.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class DeleteBookmarkEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;

    DeleteBookmarkEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.deleteBookmark(id);
    }
}
