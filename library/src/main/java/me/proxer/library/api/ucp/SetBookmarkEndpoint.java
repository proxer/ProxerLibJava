package me.proxer.library.api.ucp;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;

import javax.annotation.Nonnull;

/**
 * Endpoint for setting a bookmark.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class SetBookmarkEndpoint implements Endpoint<Void> {

    private final InternalApi internalApi;

    private final String id;
    private final int episode;
    private final MediaLanguage language;
    private final Category category;

    SetBookmarkEndpoint(@Nonnull InternalApi internalApi, @Nonnull String id, int episode,
                        @Nonnull MediaLanguage language, @Nonnull Category category) {
        this.internalApi = internalApi;
        this.id = id;
        this.episode = episode;
        this.language = language;
        this.category = category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Nonnull
    public ProxerCall<Void> build() {
        return internalApi.setBookmark(id, episode, language, category);
    }
}
