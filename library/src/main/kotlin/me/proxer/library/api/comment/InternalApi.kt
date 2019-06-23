package me.proxer.library.api.comment

import me.proxer.library.ProxerCall
import me.proxer.library.entity.info.Comment
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("comment/info")
    fun commentById(@Query("id") id: String): ProxerCall<Comment>

    @GET("comment/entry")
    fun commentByEntry(@Query("eid") entryId: String): ProxerCall<Comment>
}
