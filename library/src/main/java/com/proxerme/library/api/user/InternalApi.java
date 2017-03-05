package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.entitiy.user.User;
import com.proxerme.library.enums.Category;
import retrofit2.http.*;

import java.util.List;

interface InternalApi {

    @FormUrlEncoded
    @POST("user/login")
    ProxerCall<User> login(@Field("username") String username, @Field("password") String password);

    @POST("user/logout")
    ProxerCall<Void> logout();

    @GET("user/topten")
    ProxerCall<List<TopTenEntry>> topTen(@Query("uid") String userId, @Query("username") String username,
                                         @Query("kat") Category category);
}
