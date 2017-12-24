package me.proxer.library.api.forum;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.forum.Topic;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.annotation.ParametersAreNullableByDefault;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
interface InternalApi {

    @GET("forum/topic")
    ProxerCall<Topic> topic(@Query("id") String id,
                            @Query("p") Integer page,
                            @Query("limit") Integer limit);
}
