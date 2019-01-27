package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.TranslatorGroup

/**
 * Endpoint for retrieving all information of an [TranslatorGroup].
 *
 * @author Ruben Gees
 */
class TranslatorGroupEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<TranslatorGroup> {

    override fun build(): ProxerCall<TranslatorGroup> {
        return internalApi.translatorGroup(id)
    }
}
