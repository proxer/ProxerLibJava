package me.proxer.library.api.messenger

import me.proxer.library.ProxerCall
import me.proxer.library.entity.messenger.Conference
import me.proxer.library.entity.messenger.ConferenceInfo
import me.proxer.library.entity.messenger.Message
import me.proxer.library.enums.ConferenceType
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("messenger/conferences")
    fun conferences(
        @Query("p") page: Int?,
        @Query("type") type: ConferenceType?
    ): ProxerCall<List<Conference>>

    @GET("messenger/messages")
    fun messages(
        @Query("conference_id") conferenceId: String?,
        @Query("message_id") messageId: String?,
        @Query("read") read: Boolean?
    ): ProxerCall<List<Message>>

    @GET("messenger/conferenceinfo")
    fun conferenceInfo(@Query("conference_id") id: String?): ProxerCall<ConferenceInfo>

    @FormUrlEncoded
    @POST("messenger/newconference")
    fun createConference(
        @Field("text") firstMessage: String?,
        @Field("username") username: String?
    ): ProxerCall<String>

    @FormUrlEncoded
    @POST("messenger/newconferencegroup")
    fun createConferenceGroup(
        @Field("topic") topic: String?,
        @Field("text") firstMessage: String?,
        @Field("users[]") participants: List<String>?
    ): ProxerCall<String>

    @FormUrlEncoded
    @POST("messenger/setmessage")
    fun sendMessage(
        @Field("conference_id") conferenceId: String?,
        @Field("text") message: String?
    ): ProxerCall<String>

    @FormUrlEncoded
    @POST("messenger/setread")
    fun markConferenceAsRead(@Field("conference_id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("messenger/setunread")
    fun unmarkConferenceAsRead(@Field("conference_id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("messenger/setblock")
    fun markConferenceAsBlocked(@Field("conference_id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("messenger/setunblock")
    fun unmarkConferenceAsBlocked(@Field("conference_id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("messenger/setfavour")
    fun markConferenceAsFavorite(@Field("conference_id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("messenger/setunfavour")
    fun unmarkConferenceAsFavorite(@Field("conference_id") id: String?): ProxerCall<Unit>
}
