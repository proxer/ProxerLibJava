package me.proxer.library.api.info;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.info.Entry;
import me.proxer.library.entitiy.info.EpisodeInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Endpoint for retrieving a list of episodes and associated meta information of an {@link Entry}.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class EpisodeInfoEndpoint implements PagingLimitEndpoint<EpisodeInfo> {

    private final InternalApi internalApi;

    private final String id;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer limit;

    EpisodeInfoEndpoint(@Nonnull final InternalApi internalApi, @Nonnull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<EpisodeInfo> build() {
        return internalApi.episodeInfo(id, page, limit);
    }
}
