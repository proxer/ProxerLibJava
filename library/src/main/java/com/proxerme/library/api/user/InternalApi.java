package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.user.TopTenEntry;
import com.proxerme.library.entitiy.user.User;
import com.proxerme.library.entitiy.user.UserInfo;
import com.proxerme.library.entitiy.user.UserMediaListEntry;
import com.proxerme.library.enums.Category;
import com.proxerme.library.enums.UserMediaListSortCriteria;
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

    @GET("user/userinfo")
    ProxerCall<UserInfo> userInfo(@Query("uid") String userId, @Query("username") String username);

    @GET("user/list")
    ProxerCall<List<UserMediaListEntry>> userMediaList(@Query("uid") String userId, @Query("username") String username,
                                                       @Query("kat") Category category, @Query("p") int page,
                                                       @Query("limit") int limit, @Query("search") String query,
                                                       @Query("search_start") String startQuery,
                                                       @Query("sort") UserMediaListSortCriteria sortCriteria);
}
