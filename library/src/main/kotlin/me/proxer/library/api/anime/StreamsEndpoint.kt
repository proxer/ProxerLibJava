package me.proxer.library.api.anime

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.anime.Stream
import me.proxer.library.enums.AnimeLanguage

/**
 * Endpoint for retrieving the streams of the passed entry, concerning the episode and language.
 *
 * @author Ruben Gees
 */
class StreamsEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String,
    private val episode: Int,
    private val language: AnimeLanguage
) : Endpoint<List<Stream>> {

    private var includeProxerStreams: Boolean? = null

    /**
     * Sets if the Proxer streams should be included in the result.
     *
     * Note, that those require higher access levels.
     */
    fun includeProxerStreams(includeProxerStreams: Boolean? = true) = this.apply {
        this.includeProxerStreams = includeProxerStreams
    }

    override fun build(): ProxerCall<List<Stream>> {
        return when (includeProxerStreams) {
            true -> internalApi.proxerStreams(id, episode, language)
            else -> internalApi.streams(id, episode, language)
        }
    }
}
