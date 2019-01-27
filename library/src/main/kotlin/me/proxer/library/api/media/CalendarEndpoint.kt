package me.proxer.library.api.media

import me.proxer.library.api.Endpoint
import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.media.CalendarEntry

/**
 * Endpoint for retrieving the anime calendar/schedule.
 *
 * @author Ruben Gees
 */
class CalendarEndpoint internal constructor(private val internalApi: InternalApi) : Endpoint<List<CalendarEntry>> {

    override fun build(): ProxerCall<List<CalendarEntry>> {
        return internalApi.calendar()
    }
}
