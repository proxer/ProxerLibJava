package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;

/**
 * Endpoint for deleting a comment. Does also delete the associated {link {@link me.proxer.library.entity.info.Entry}}
 * from the list of the user.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class DeleteCommentEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;

    DeleteCommentEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Void> build() {
        return internalApi.deleteComment(id);
    }
}
