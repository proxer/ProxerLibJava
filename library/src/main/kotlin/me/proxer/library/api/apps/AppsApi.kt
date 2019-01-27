package me.proxer.library.api.apps

import retrofit2.Retrofit

/**
 * Api for the Media class.
 *
 * @author Ruben Gees
 */
class AppsApi internal constructor(retrofit: Retrofit) {

    private val internalApi: InternalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun errorLog(id: String, message: String): ErrorLogEndpoint {
        return ErrorLogEndpoint(internalApi, id, message)
    }
}
