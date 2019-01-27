package me.proxer.library.api.media

import retrofit2.Retrofit

/**
 * Api for the Media class.
 *
 * @author Ruben Gees
 */
class MediaApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun calendar(): CalendarEndpoint {
        return CalendarEndpoint(internalApi)
    }
}
