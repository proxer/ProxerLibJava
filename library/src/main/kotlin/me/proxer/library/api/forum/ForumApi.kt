package me.proxer.library.api.forum

import retrofit2.Retrofit

/**
 * API for the Forum class.
 *
 * @author Ruben Gees
 */
class ForumApi internal constructor(retrofit: Retrofit) {

    private val internalApi: InternalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun topic(id: String) = TopicEndpoint(internalApi, id)
}
