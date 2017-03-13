package com.proxerme.library.api.list;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.TranslatorGroupProject;
import com.proxerme.library.enums.ProjectState;
import lombok.Setter;
import lombok.experimental.Accessors;
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
    @Setter(onMethod = @__({@NotNull}))
    private Boolean includeHentai;

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
}
