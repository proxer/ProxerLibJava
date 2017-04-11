package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.IndustryProject;
import me.proxer.library.enums.IndustryType;
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

    IndustryProjectListEndpoint(@NotNull final InternalApi internalApi, @NotNull final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @Override
    @NotNull
    public ProxerCall<List<IndustryProject>> build() {
        return internalApi.industryProjectList(id, type, includeHentai, page, limit);
    }

    /**
     * Sets if hentai should be included in the result.
     */
    @NotNull
    public IndustryProjectListEndpoint includeHentai(@Nullable Boolean includeHentai) {
        if (includeHentai == null) {
            this.includeHentai = null;
        } else {
            this.includeHentai = includeHentai ? 0 : -1;
        }

        return this;
    }
}
