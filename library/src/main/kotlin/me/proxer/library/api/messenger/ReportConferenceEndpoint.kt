package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint

/**
 * Endpoint for reporting a conference. The given text is sent to the moderators for investigation.
 *
 * @author Ruben Gees
 */
class ReportConferenceEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val conferenceId: String,
    private val text: String
) : Endpoint<Unit?> {

    override fun build(): ProxerCall<Unit?> {
        return internalApi.report(conferenceId, text)
    }
}
