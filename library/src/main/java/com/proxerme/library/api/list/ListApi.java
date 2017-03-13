package com.proxerme.library.api.list;

import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;

/**
 * API for the List class.
 *
 * @author Desnoo.
 */
public class ListApi {

    private final InternalApi internalApi;

    public ListApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public MediaListEndpoint mediaList() {
        return new MediaListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public MediaSearchEndpoint mediaSearch() {
        return new MediaSearchEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public TranslatorGroupListEndpoint translatorGroupList() {
        return new TranslatorGroupListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public IndustryListEndpoint industryList() {
        return new IndustryListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public TranslatorGroupProjectListEndpoint translatorGroupProjectList(@NotNull final String translatorGroupId) {
        return new TranslatorGroupProjectListEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public IndustryProjectListEndpoint industryProjectList(@NotNull final String industryId) {
        return new IndustryProjectListEndpoint(internalApi, industryId);
    }
}
