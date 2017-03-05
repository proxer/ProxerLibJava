package com.proxerme.library.api.notifications;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.notifications.NewsArticle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class NewsEndpoint {

    private InternalApi internalApi;

    private Integer page;
    private Integer limit;

    public NewsEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @NotNull
    public NewsEndpoint page(@Nullable final Integer page) {
        this.page = page;

        return this;
    }

    @NotNull
    public NewsEndpoint limit(@Nullable final Integer limit) {
        this.limit = limit;

        return this;
    }

    @NotNull
    public ProxerCall<List<NewsArticle>> build() {
        return new ProxerCall<>(internalApi.news(page, limit));
    }
}
