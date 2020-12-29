package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage

/**
 * Endpoint for setting a bookmark.
 *
 * @author Ruben Gees
 */
class SetBookmarkEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String,
    private val episode: Int,
    private val language: MediaLanguage,
    private val category: Category
) : Endpoint<Unit?> {

    override fun build(): ProxerCall<Unit?> {
        return internalApi.setBookmark(id, episode, language, category)
    }
}
