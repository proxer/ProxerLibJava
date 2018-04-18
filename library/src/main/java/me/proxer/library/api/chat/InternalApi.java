package me.proxer.library.api.chat;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.chat.ChatMessage;
import me.proxer.library.entity.chat.ChatRoom;
import me.proxer.library.entity.chat.ChatRoomUser;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
public interface InternalApi {

    @GET("chat/publicrooms")
    ProxerCall<List<ChatRoom>> publicRooms();

    @GET("chat/myrooms")
    ProxerCall<List<ChatRoom>> userRooms();

    @GET("chat/messages")
    ProxerCall<List<ChatMessage>> messages(@Query("room_id") String roomId,
                                           @Query("message_id") String messageId);

    @FormUrlEncoded
    @POST("chat/newmessage")
    ProxerCall<String> sendMessage(@Field("room_id") String roomId,
                                   @Field("message") String message);

    @FormUrlEncoded
    @POST("chat/reportmessage")
    ProxerCall<String> reportMessage(@Field("message_id") String messageId,
                                     @Field("message") String message);

    @GET("chat/roomusers")
    ProxerCall<List<ChatRoomUser>> roomUsers(@Query("room_id") String roomId);
}
