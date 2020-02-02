package me.proxer.library.api.info

import retrofit2.Retrofit

/**
 * API for the Info class.
 *
 * @author Ruben Gees
 */
class InfoApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun entryCore(entryId: String): EntryCoreEndpoint {
        return EntryCoreEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun entry(entryId: String): EntryEndpoint {
        return EntryEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun episodeInfo(entryId: String): EpisodeInfoEndpoint {
        return EpisodeInfoEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun comments(entryId: String): CommentsEndpoint {
        return CommentsEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun relations(entryId: String): RelationsEndpoint {
        return RelationsEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun translatorGroup(translatorGroupId: String): TranslatorGroupEndpoint {
        return TranslatorGroupEndpoint(internalApi, translatorGroupId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun industry(industryId: String): IndustryEndpoint {
        return IndustryEndpoint(internalApi, industryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun note(entryId: String): ModifyUserInfoEndpoint {
        return ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.NOTE)
    }

    /**
     * Returns the respective endpoint.
     */
    fun markAsFavorite(entryId: String): ModifyUserInfoEndpoint {
        return ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FAVORITE)
    }

    /**
     * Returns the respective endpoint.
     */
    fun markAsFinished(entryId: String): ModifyUserInfoEndpoint {
        return ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.FINISHED)
    }

    /**
     * Returns the respective endpoint.
     */
    fun subscribe(entryId: String): ModifyUserInfoEndpoint {
        return ModifyUserInfoEndpoint(internalApi, entryId, UserInfoType.SUBSCRIBE)
    }

    /**
     * Returns the respective endpoint.
     */
    fun recommendations(entryId: String): RecommendationsEndpoint {
        return RecommendationsEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun userInfo(entryId: String): MediaUserInfoEndpoint {
        return MediaUserInfoEndpoint(internalApi, entryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun forumDiscussions(entryId: String): ForumDiscussionsEndpoint {
        return ForumDiscussionsEndpoint(internalApi, entryId)
    }
}
