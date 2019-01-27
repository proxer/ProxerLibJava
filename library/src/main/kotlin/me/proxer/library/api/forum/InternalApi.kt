package me.proxer.library.api.forum

import me.proxer.library.api.ProxerCall
import me.proxer.library.entity.forum.Topic
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("forum/topic")
    fun topic(
        @Query("id") id: String?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<Topic>
}
