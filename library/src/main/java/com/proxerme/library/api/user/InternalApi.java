package com.proxerme.library.api.user;

import com.proxerme.library.entitiy.ProxerResponse;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.entitiy.user.User;
import com.proxerme.library.enums.Category;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

interface InternalApi {

    @FormUrlEncoded
    @POST("user/login")
    Call<ProxerResponse<User>> login(@Field("username") String username, @Field("password") String password);

    @POST("user/logout")
    Call<ProxerResponse<Void>> logout();

    @GET("user/topten")
    Call<ProxerResponse<List<TopTenEntry>>> topTen(@Query("uid") String userId, @Query("username") String username,
                                                   @Query("kat") Category category);
}
