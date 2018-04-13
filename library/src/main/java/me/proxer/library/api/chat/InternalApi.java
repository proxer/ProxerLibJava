package me.proxer.library.api.chat;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.chat.ChatRoom;
import retrofit2.http.GET;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
public interface InternalApi {

    @GET("chat/publicrooms")
    ProxerCall<List<ChatRoom>> publicRooms();
}
