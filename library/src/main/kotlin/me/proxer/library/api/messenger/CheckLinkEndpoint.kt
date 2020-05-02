package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.messenger.LinkCheckResponse

/**
 * Endpoint for reporting a conference. The given text is sent to the moderators for investigation.
 *
 * @author Ruben Gees
 */
class CheckLinkEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val link: String
) : Endpoint<LinkCheckResponse> {

    override fun build(): ProxerCall<LinkCheckResponse> {
        return internalApi.checkLink(link)
    }
}
