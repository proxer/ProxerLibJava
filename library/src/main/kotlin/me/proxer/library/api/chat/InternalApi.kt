package me.proxer.library.api.chat

import me.proxer.library.ProxerCall
import me.proxer.library.entity.chat.ChatMessage
import me.proxer.library.entity.chat.ChatRoom
import me.proxer.library.entity.chat.ChatRoomUser
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("chat/publicrooms")
    fun publicRooms(): ProxerCall<List<ChatRoom>>

    @GET("chat/myrooms")
    fun userRooms(): ProxerCall<List<ChatRoom>>

    @GET("chat/messages")
    fun messages(
        @Query("room_id") roomId: String?,
        @Query("message_id") messageId: String?
    ): ProxerCall<List<ChatMessage>>

    @FormUrlEncoded
    @POST("chat/newmessage")
    fun sendMessage(
        @Field("room_id") roomId: String?,
        @Field("message") message: String?
    ): ProxerCall<String>

    @FormUrlEncoded
    @POST("chat/reportmessage")
    fun reportMessage(
        @Field("message_id") messageId: String?,
        @Field("message") message: String?
    ): ProxerCall<Unit>

    @GET("chat/roomusers")
    fun roomUsers(@Query("room_id") roomId: String?): ProxerCall<List<ChatRoomUser>>
}
