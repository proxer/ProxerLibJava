package me.proxer.library.api.comment

import me.proxer.library.ProxerCall
import me.proxer.library.entity.info.Comment
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("comments/info")
    fun commentById(@Query("id") id: String): ProxerCall<Comment>

    @GET("comments/entry")
    fun commentByEntry(@Query("eid") entryId: String): ProxerCall<Comment>

    @FormUrlEncoded
    @POST("comments/create")
    fun create(
        @Field("eid") entryId: String,
        @Field("rating") rating: Int,
        @Field("episode") episode: Int,
        @Field("state") state: Int,
        @Field("comment") comment: String
    ): ProxerCall<Unit>
}
