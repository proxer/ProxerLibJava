package me.proxer.library.api.info;

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
    public EntryCoreEndpoint entryCore(@NotNull final String entryId) {
        return new EntryCoreEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public EntryEndpoint entry(@NotNull final String entryId) {
        return new EntryEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public EpisodeInfoEndpoint episodeInfo(@NotNull final String entryId) {
        return new EpisodeInfoEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public CommentsEndpoint comments(@NotNull final String entryId) {
        return new CommentsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public RelationsEndpoint relations(@NotNull final String entryId) {
        return new RelationsEndpoint(internalApi, entryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public TranslatorGroupEndpoint translatorGroup(@NotNull final String translatorGroupId) {
        return new TranslatorGroupEndpoint(internalApi, translatorGroupId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public IndustryEndpoint industry(@NotNull final String industryId) {
        return new IndustryEndpoint(internalApi, industryId);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ModifyUserInfoEndpoint note(@NotNull final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.NOTE);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ModifyUserInfoEndpoint markAsFavorite(@NotNull final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FAVORITE);
    }

    /**
     * Returns the respective endpoint.
     */
    @NotNull
    public ModifyUserInfoEndpoint markAsFinished(@NotNull final String entryId) {
        return new ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FINISHED);
    }
}
