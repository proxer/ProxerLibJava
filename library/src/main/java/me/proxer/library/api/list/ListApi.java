package me.proxer.library.api.list;

import retrofit2.Retrofit;

/**
 * API for the List class.
 *
 * @author Desnoo.
 */
public final class ListApi {

    private final InternalApi internalApi;

    public ListApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public MediaListEndpoint mediaList() {
        return new MediaListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public MediaSearchEndpoint mediaSearch() {
        return new MediaSearchEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public TranslatorGroupListEndpoint translatorGroupList() {
        return new TranslatorGroupListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public IndustryListEndpoint industryList() {
        return new IndustryListEndpoint(internalApi);
    }

    /**
     * Returns the respective endpoint.
     */
    public TranslatorGroupProjectListEndpoint translatorGroupProjectList(final String translatorGroupId) {
        return new TranslatorGroupProjectListEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    public IndustryProjectListEndpoint industryProjectList(final String industryId) {
        return new IndustryProjectListEndpoint(internalApi, industryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public TagListEndpoint tagList() {
        return new TagListEndpoint(internalApi);
    }
}
