package com.proxerme.library.api.list;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * TODO: Describe class
 *
 * @author Desnoo.
 */
public class ListApi {

    private final InternalApi internalApi;

    public ListApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public EntryListEndpoint mediaEntries(Integer page) {
        return new EntryListEndpoint(internalApi).page(page);
    }

    @NotNull
    public EntrySearchEndpoint searchEntries(Integer page) { return new EntrySearchEndpoint(internalApi).page(page); }
}
