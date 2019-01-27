package me.proxer.library.api.info

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.info.Entry
import me.proxer.library.entity.info.EntryCore

/**
 * Endpoint for retrieving the most important information of an [Entry].
 *
 * If you need everything, consider using the [EntryEndpoint].
 *
 * @author Ruben Gees
 */
class EntryCoreEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<EntryCore> {

    override fun build(): ProxerCall<EntryCore> {
        return internalApi.entryCore(id)
    }
}
