package com.proxerme.library.api.list;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.IndustryProject;
import com.proxerme.library.enums.IndustryType;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving a list of projects of an industry.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class IndustryProjectListEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    private final String id;

    /**
     * Sets the type of industries to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private IndustryType type;

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

    IndustryProjectListEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @Override
    @NotNull
    public ProxerCall<List<IndustryProject>> build() {
        return internalApi.industryProjectList(id, type, includeHentai, page, limit);

    }
}
