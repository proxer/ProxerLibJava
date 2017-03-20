package me.proxer.library.api.list;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.LimitEndpoint;
import me.proxer.library.api.PagingEndpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.TranslatorGroupCore;
import me.proxer.library.enums.Country;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving a list of translator groups.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public class TranslatorGroupListEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * Sets the query to search for only from the start.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private String searchStart;

    /**
     * Sets the query to search for.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private String search;

    /**
     * Sets the country to filter by.
     */
    @Nullable
    @Setter(onMethod = @__({@NotNull}))
    private Country country;

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

    TranslatorGroupListEndpoint(@NotNull final InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    @Override
    @NotNull
    public ProxerCall<List<TranslatorGroupCore>> build() {
        return internalApi.translatorGroupList(searchStart, search, country, page, limit);

    }
}
