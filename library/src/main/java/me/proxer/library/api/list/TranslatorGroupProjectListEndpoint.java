package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.TranslatorGroupProject;
import me.proxer.library.enums.ProjectState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving a list of projects of a translator group.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupProjectListEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    private final String id;

    /**
     * Sets the state of projects to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
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
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Setter(onMethod = @__({@Override, @NotNull}))
    private Integer limit;

    TranslatorGroupProjectListEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @Override
    @NotNull
    public ProxerCall<List<TranslatorGroupProject>> build() {
        return internalApi.translatorGroupProjectList(id, projectState, includeHentai, page, limit);
    }

    /**
     * Sets if hentai should be included in the result.
     */
    @NotNull
    public TranslatorGroupProjectListEndpoint includeHentai(@Nullable Boolean includeHentai) {
        if (includeHentai == null) {
            this.includeHentai = null;
        } else {
            this.includeHentai = includeHentai ? 0 : -1;
        }

        return this;
    }
}
