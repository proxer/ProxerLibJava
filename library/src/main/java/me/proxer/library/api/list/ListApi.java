package me.proxer.library.api.list;

import retrofit2.Retrofit;

import javax.annotation.Nonnull;

/**
 * API for the List class.
 *
 * @author Desnoo.
 */
public class ListApi {

    private final InternalApi internalApi;

    public ListApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public MediaListEndpoint mediaList() {
        return new MediaListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public MediaSearchEndpoint mediaSearch() {
        return new MediaSearchEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public TranslatorGroupListEndpoint translatorGroupList() {
        return new TranslatorGroupListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public IndustryListEndpoint industryList() {
        return new IndustryListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public TranslatorGroupProjectListEndpoint translatorGroupProjectList(@Nonnull final String translatorGroupId) {
        return new TranslatorGroupProjectListEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public IndustryProjectListEndpoint industryProjectList(@Nonnull final String industryId) {
        return new IndustryProjectListEndpoint(internalApi, industryId);
    }
}
