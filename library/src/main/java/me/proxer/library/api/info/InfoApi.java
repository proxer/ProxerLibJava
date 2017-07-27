package me.proxer.library.api.info;

import retrofit2.Retrofit;

import javax.annotation.Nonnull;

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
    public InfoApi(@Nonnull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public EntryCoreEndpoint entryCore(@Nonnull final String entryId) {
        return new EntryCoreEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public EntryEndpoint entry(@Nonnull final String entryId) {
        return new EntryEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public EpisodeInfoEndpoint episodeInfo(@Nonnull final String entryId) {
        return new EpisodeInfoEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public CommentsEndpoint comments(@Nonnull final String entryId) {
        return new CommentsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public RelationsEndpoint relations(@Nonnull final String entryId) {
        return new RelationsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public TranslatorGroupEndpoint translatorGroup(@Nonnull final String translatorGroupId) {
        return new TranslatorGroupEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public IndustryEndpoint industry(@Nonnull final String industryId) {
        return new IndustryEndpoint(internalApi, industryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ModifyUserInfoEndpoint note(@Nonnull final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.NOTE);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ModifyUserInfoEndpoint markAsFavorite(@Nonnull final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FAVORITE);
    }

    /**
     * Returns the respective endpoint.
     */
    @Nonnull
    public ModifyUserInfoEndpoint markAsFinished(@Nonnull final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FINISHED);
    }
}
