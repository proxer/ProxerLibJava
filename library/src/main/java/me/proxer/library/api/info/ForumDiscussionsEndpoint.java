package me.proxer.library.api.info;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.info.ForumDiscussion;

import java.util.List;

/**
 * Endpoint for retrieving the list of forum discussions associated with an {@link me.proxer.library.entity.info.Entry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ForumDiscussionsEndpoint implements Endpoint<List<ForumDiscussion>> {

    private final InternalApi internalApi;

    private final String id;

    ForumDiscussionsEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<ForumDiscussion>> build() {
        return internalApi.forumDiscussions(id);
    }
}
