package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.TranslatorGroupProject;
import me.proxer.library.enums.ProjectState;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving a list of projects of a translator group.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class TranslatorGroupProjectListEndpoint implements PagingLimitEndpoint<List<TranslatorGroupProject>> {

    private final InternalApi internalApi;

    private final String id;

    /**
     * Sets the state of projects to filter by.
     */
    @Nullable
    @Setter
    private ProjectState projectState;

    /**
     * Sets if hentai should be included in the result.
     */
    @Nullable
    private Integer includeHentai;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter
    private Integer limit;

    TranslatorGroupProjectListEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @Override
    public ProxerCall<List<TranslatorGroupProject>> build() {
        return internalApi.translatorGroupProjectList(id, projectState, includeHentai, page, limit);
    }

    /**
     * Sets if hentai should be included in the result.
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public TranslatorGroupProjectListEndpoint includeHentai(@Nullable final Boolean includeHentai) {
        if (includeHentai == null) {
            this.includeHentai = null;
        } else {
            this.includeHentai = includeHentai ? 0 : -1;
        }

        return this;
    }
}
