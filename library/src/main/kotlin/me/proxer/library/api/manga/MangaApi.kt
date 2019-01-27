package me.proxer.library.api.manga

import me.proxer.library.enums.Language
import retrofit2.Retrofit

/**
 * API for the Manga class.
 *
 * @author Ruben Gees
 */
class MangaApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun chapter(entryId: String, episode: Int, language: Language): ChapterEndpoint {
        return ChapterEndpoint(internalApi, entryId, episode, language)
    }
}
