package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

import javax.annotation.Nonnull;

/**
 * Endpoint for deleting a bookmark.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class DeleteBookmarkEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;

    DeleteBookmarkEndpoint(@Nonnull final InternalApi internalApi, @Nonnull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<Void> build() {
        return internalApi.deleteBookmark(id);
    }
}
