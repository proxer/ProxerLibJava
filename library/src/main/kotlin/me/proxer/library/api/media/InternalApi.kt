package me.proxer.library.api.media

import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.media.CalendarEntry
import retrofit2.http.GET

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @GET("media/calendar")
    fun calendar(): ProxerCall<List<CalendarEntry>>
}
