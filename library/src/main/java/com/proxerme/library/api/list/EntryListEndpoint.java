package com.proxerme.library.api.list;

import com.proxerme.library.api.LimitEndpoint;
import com.proxerme.library.api.PagingEndpoint;
import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.MediaEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.Medium;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Endpoint for retrieving the entries of a search. As type of {@link com.proxerme.library.entitiy.list.MediaEntry}.
 *
 * @author Desnoo
 */
@Accessors(fluent = true)
public class EntryListEndpoint implements PagingEndpoint, LimitEndpoint {

    private final InternalApi internalApi;

    /**
     * Sets the category to search.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Category category;

    /**
     * Sets the medium.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Medium medium;

    /**
     * Sets if hentai is searched.
     */
    @Setter(onMethod = @__({@Nullable}))
    private Boolean hentai;

    /**
     * Sets the string to sarch for.
     */
    @Setter(onMethod = @__({@Nullable}))
    private String searchStartString;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer page;

    /**
     * {@inheritDoc}
     */
    @Setter(onMethod = @__({@Override, @Nullable}))
    private Integer limit;

    /**
     * The constructor.
     */
    public EntryListEndpoint(InternalApi internalApi) {
        this.internalApi = internalApi;
    }

    /**
     * {@inheritDoc}
     */
    @NotNull
    public ProxerCall<List<MediaEntry>> build() {
        return internalApi.mediaEntries(category, medium, hentai, searchStartString, page, limit);
    }
}

