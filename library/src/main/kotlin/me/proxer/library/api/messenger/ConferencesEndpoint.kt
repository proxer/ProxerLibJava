package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingEndpoint
import me.proxer.library.entity.messenger.Conference
import me.proxer.library.enums.ConferenceType

/**
 * Endpoint for retrieving the user's conferences.
 *
 * @author Ruben Gees
 */
class ConferencesEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingEndpoint<List<Conference>> {

    private var page: Int? = null

    private var type: ConferenceType? = null

    override fun page(page: Int?) = this.apply { this.page = page }

    /**
     * Sets the type of conferences to load.
     */
    fun type(type: ConferenceType?) = this.apply { this.type = type }

    override fun build(): ProxerCall<List<Conference>> {
        return internalApi.conferences(page, type)
    }
}
