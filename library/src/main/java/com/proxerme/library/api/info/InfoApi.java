package com.proxerme.library.api.info;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class InfoApi {

    private final InternalApi internalApi;

    public InfoApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public CommentsEndpoint comments(@NotNull String entryId) {
        return new CommentsEndpoint(internalApi, entryId);
    }

    @NotNull
    public EntryCoreEndpoint entryCore(@NotNull String entryId) {
        return new EntryCoreEndpoint(internalApi, entryId);
    }

    @NotNull
    public EntryEndpoint entry(@NotNull String entryId) {
        return new EntryEndpoint(internalApi, entryId);
    }
}
