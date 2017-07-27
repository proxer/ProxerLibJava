package me.proxer.library.api.notifications;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.notifications.NewsArticle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving news articles.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class NewsEndpoint implements PagingLimitEndpoint<List<NewsArticle>> {

    private final InternalApi internalApi;

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

    NewsEndpoint(@Nonnull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<List<NewsArticle>> build() {
        return internalApi.news(page, limit);
    }
}
