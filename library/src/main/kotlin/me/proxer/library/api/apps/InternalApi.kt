package me.proxer.library.api.apps

import me.proxer.library.ProxerCall
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @FormUrlEncoded
    @POST("apps/errorlog")
    fun errorLog(
        @Field("id") id: String?,
        @Field("message") message: String?,
        @Field("anonym") anonym: Boolean?,
        @Field("silent") silent: Boolean?
    ): ProxerCall<Unit>
}
