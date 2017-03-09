package com.proxerme.library.api.notifications;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.notifications.NewsArticle;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving news articles.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class NewsEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer limit;

    NewsEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<List<NewsArticle>> build() {
        return internalApi.news(page, limit);
    }
}
