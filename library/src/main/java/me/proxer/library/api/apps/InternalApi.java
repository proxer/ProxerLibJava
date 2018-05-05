package me.proxer.library.api.apps;

import me.proxer.library.api.ProxerCall;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import javax.annotation.ParametersAreNullableByDefault;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
public interface InternalApi {

    @FormUrlEncoded
    @POST("apps/errorlog")
    ProxerCall<Void> errorLog(@Field("id") String id,
                              @Field("message") String message,
                              @Field("anonym") Boolean anonym,
                              @Field("silent") Boolean silent);
}
