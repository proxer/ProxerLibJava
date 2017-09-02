package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.PagingLimitEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.TranslatorGroupCore;
import me.proxer.library.enums.Country;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving a list of translator groups.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupListEndpoint implements PagingLimitEndpoint<List<TranslatorGroupCore>> {

    private final InternalApi internalApi;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter
    private String searchStart;

    /**
     * Sets the query to search for.
     */
    @Nullable
    @Setter
    private String search;

    /**
     * Sets the country to filter by.
     */
    @Nullable
    @Setter
    private Country country;

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

    TranslatorGroupListEndpoint(final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @Override
    public ProxerCall<List<TranslatorGroupCore>> build() {
        return internalApi.translatorGroupList(searchStart, search, country, page, limit);
    }
}
