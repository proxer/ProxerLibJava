package me.proxer.library.api.info;

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
    public InfoApi(final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    public EntryCoreEndpoint entryCore(final String entryId) {
        return new EntryCoreEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public EntryEndpoint entry(final String entryId) {
        return new EntryEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public EpisodeInfoEndpoint episodeInfo(final String entryId) {
        return new EpisodeInfoEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public CommentsEndpoint comments(final String entryId) {
        return new CommentsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public RelationsEndpoint relations(final String entryId) {
        return new RelationsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public TranslatorGroupEndpoint translatorGroup(final String translatorGroupId) {
        return new TranslatorGroupEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    public IndustryEndpoint industry(final String industryId) {
        return new IndustryEndpoint(internalApi, industryId);
    }

    /**
     * Returns the respective endpoint.
     */
    public ModifyUserInfoEndpoint note(final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.NOTE);
    }

    /**
     * Returns the respective endpoint.
     */
    public ModifyUserInfoEndpoint markAsFavorite(final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FAVORITE);
    }

    /**
     * Returns the respective endpoint.
     */
    public ModifyUserInfoEndpoint markAsFinished(final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FINISHED);
    }

    /**
     * Returns the respective endpoint.
     */
    public RecommendationsEndpoint recommendations(final String entryId) {
        return new RecommendationsEndpoint(internalApi, entryId);
    }
}
