package me.proxer.library.api.manga;

import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.manga.Chapter;
import me.proxer.library.enums.Language;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for retrieving the chapter corresponding to the passed entryId, episode and language.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class ChapterEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String id;
    private final int episode;
    private final Language language;

    ChapterEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id, final int episode,
                    @NotNull final Language language) {
        this.internalApi = internalApi;
        this.id = id;
        this.episode = episode;
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<Chapter> build() {
        return internalApi.chapter(id, episode, language);
    }
}
