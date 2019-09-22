package me.proxer.library.api.wiki

import retrofit2.Retrofit

/**
 * API for the User class.
 *
 * @author Ruben Gees
 */
class WikiApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun content(title: String): WikiContentEndpoint {
        return WikiContentEndpoint(internalApi, title)
    }
}
