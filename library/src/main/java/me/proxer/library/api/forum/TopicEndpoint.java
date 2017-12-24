package me.proxer.library.api.forum;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.forum.Topic;

import javax.annotation.Nullable;

/**
 * Endpoint for retrieving a forum topic with its posts.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class TopicEndpoint implements PagingLimitEndpoint<Topic> {

    private final InternalApi internalApi;

    private final String id;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override}))
    private Integer limit;

    TopicEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<Topic> build() {
        return internalApi.topic(id, page, limit);
    }
}
