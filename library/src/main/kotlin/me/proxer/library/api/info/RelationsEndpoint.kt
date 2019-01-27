package me.proxer.library.api.info

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.info.Entry
import me.proxer.library.entity.info.Relation

/**
 * Endpoint for retrieving the relations of an [Entry].
 *
 * @author Ruben Gees
 */
class RelationsEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<List<Relation>> {

    private var includeHentai: Boolean? = null

    /**
     * Sets if hentai should be included in the result.
     */
    fun includeHentai(includeHentai: Boolean? = true) = this.apply { this.includeHentai = includeHentai }

    override fun build(): ProxerCall<List<Relation>> {
        return internalApi.relations(id, includeHentai)
    }
}
