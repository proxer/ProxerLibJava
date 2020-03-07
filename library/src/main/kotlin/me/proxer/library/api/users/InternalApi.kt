package me.proxer.library.api.users

import me.proxer.library.ProxerCall
import retrofit2.http.GET

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("users/teamdescriptions")
    fun teamDescriptions(): ProxerCall<Map<String, String>>
}
