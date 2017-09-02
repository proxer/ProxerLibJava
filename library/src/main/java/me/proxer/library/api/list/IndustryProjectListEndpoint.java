package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.IndustryProject;
import me.proxer.library.enums.IndustryType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving a list of projects of an industry.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class IndustryProjectListEndpoint implements PagingLimitEndpoint<List<IndustryProject>> {

    private final InternalApi internalApi;

    private final String id;

    /**
     * Sets the type of industries to filter by.
     */
    @Nullable
    @Setter
    private IndustryType type;

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

    IndustryProjectListEndpoint(final InternalApi internalApi, final String id) {
        this.internalApi = internalApi;
        this.id = id;
    }

    @Override
    public ProxerCall<List<IndustryProject>> build() {
        return internalApi.industryProjectList(id, type, includeHentai, page, limit);
    }

    /**
     * Sets if hentai should be included in the result.
     */
    @SuppressWarnings("checkstyle:hiddenfield")
    public IndustryProjectListEndpoint includeHentai(@Nullable final Boolean includeHentai) {
        if (includeHentai == null) {
            this.includeHentai = null;
        } else {
            this.includeHentai = includeHentai ? 0 : -1;
        }

        return this;
    }
}
