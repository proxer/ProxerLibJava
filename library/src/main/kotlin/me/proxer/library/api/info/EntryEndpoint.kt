package me.proxer.library.api.info

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.info.Entry

/**
 * Endpoint for retrieving all information of an [Entry].
 *
 * If you do not need everything, consider using the [EntryCoreEndpoint].
 *
 * @author Ruben Gees
 */
class EntryEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Entry> {

    override fun build(): ProxerCall<Entry> {
        return internalApi.entry(id)
    }
}
