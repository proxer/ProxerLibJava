package me.proxer.library.api.chat;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.chat.ChatMessage;
import me.proxer.library.entity.chat.ChatRoom;
import retrofit2.http.GET;
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

    @GET("chat/messages")
    ProxerCall<List<ChatMessage>> messages(@Query("room_id") String roomId,
                                           @Query("message_id") String messageId);
}
