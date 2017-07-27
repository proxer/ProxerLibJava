package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.TranslatorGroupProject;
import me.proxer.library.enums.ProjectState;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving a list of projects of a translator group.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupProjectListEndpoint implements PagingEndpoint<List<TranslatorGroupProject>>,
        LimitEndpoint<List<TranslatorGroupProject>> {

    private final InternalApi internalApi;

    private final String id;

    /**
     * Sets the state of projects to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@Nonnull}))
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
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @Nonnull}))
    private Integer limit;

    TranslatorGroupProjectListEndpoint(@Nonnull final InternalApi internalApi, @Nonnull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @Override
    @Nonnull
    public ProxerCall<List<TranslatorGroupProject>> build() {
        return internalApi.translatorGroupProjectList(id, projectState, includeHentai, page, limit);
    }

    /**
     * Sets if hentai should be included in the result.
     */
    @Nonnull
    public TranslatorGroupProjectListEndpoint includeHentai(@Nullable Boolean includeHentai) {
        if (includeHentai == null) {
            this.includeHentai = null;
        } else {
            this.includeHentai = includeHentai ? 0 : -1;
        }

        return this;
    }
}
