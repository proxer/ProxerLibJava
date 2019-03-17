package me.proxer.library.api.anime

import me.proxer.library.enums.AnimeLanguage
import retrofit2.Retrofit

/**
 * API for the Anime class.
 *
 * @author Ruben Gees
 */
class AnimeApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun streams(entryId: String, episode: Int, language: AnimeLanguage): StreamsEndpoint {
        return StreamsEndpoint(internalApi, entryId, episode, language)
    }

    /**
     * Returns the respective endpoint.
     */
    fun link(streamId: String): LinkEndpoint {
        return LinkEndpoint(internalApi, streamId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun vastLink(streamId: String): VastLinkEndpoint {
        return VastLinkEndpoint(internalApi, streamId)
    }
}
