package com.proxerme.library.api.info;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * API for the Info class.
 *
 * @author Ruben Gees
 */
public final class InfoApi {

    private final InternalApi internalApi;

    /**
     * Only for internal use.
     */
    public InfoApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public CommentsEndpoint comments(@NotNull String entryId) {
        return new CommentsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public EntryCoreEndpoint entryCore(@NotNull String entryId) {
        return new EntryCoreEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public EntryEndpoint entry(@NotNull String entryId) {
        return new EntryEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public TranslatorGroupEndpoint translatorGroup(@NotNull String translatorGroupId) {
        return new TranslatorGroupEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public IndustryEndpoint industry(@NotNull String industryId) {
        return new IndustryEndpoint(internalApi, industryId);
    }
}
