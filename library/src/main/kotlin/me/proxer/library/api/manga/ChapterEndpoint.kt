package me.proxer.library.api.manga

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.manga.Chapter
import me.proxer.library.enums.Language

/**
 * Endpoint for retrieving the chapter corresponding to the passed entryId, episode and language.
 *
 * @author Ruben Gees
 */
class ChapterEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String,
    private val episode: Int,
    private val language: Language
) : Endpoint<Chapter> {

    override fun build(): ProxerCall<Chapter> {
        return internalApi.chapter(id, episode, language)
    }
}
