package me.proxer.library.api.users

import retrofit2.Retrofit

/**
 * API for the Users class.
 *
 * @author Ruben Gees
 */
class UsersApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun teamDescriptions(): TeamDescriptionEndpoint {
        return TeamDescriptionEndpoint(internalApi)
    }
}
