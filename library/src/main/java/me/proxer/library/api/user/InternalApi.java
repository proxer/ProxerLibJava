package me.proxer.library.api.user;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.user.*;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.UserMediaListFilterType;
import me.proxer.library.enums.UserMediaListSortCriteria;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @FormUrlEncoded
    @POST("user/login")
    ProxerCall<User> login(@Field("username") String username, @Field("password") String password,
                           @Field("secretkey") String secretKey);

    @POST("user/logout")
    ProxerCall<Void> logout();

    @GET("user/topten")
    ProxerCall<List<TopTenEntry>> topTen(@Query("uid") String userId, @Query("username") String username,
                                         @Query("kat") Category category, @Query("isH") Boolean includeHentai);

    @GET("user/userinfo")
    ProxerCall<UserInfo> userInfo(@Query("uid") String userId, @Query("username") String username);

    @GET("user/list")
    ProxerCall<List<UserMediaListEntry>> userMediaList(@Query("uid") String userId, @Query("username") String username,
                                                       @Query("kat") Category category, @Query("p") Integer page,
                                                       @Query("limit") Integer limit, @Query("search") String query,
                                                       @Query("search_start") String startQuery,
                                                       @Query("filter") UserMediaListFilterType filter,
                                                       @Query("sort") UserMediaListSortCriteria sortCriteria,
                                                       @Query("isH") Boolean includeHentai);

    @GET("user/comments")
    ProxerCall<List<UserComment>> comments(@Query("uid") String userId, @Query("username") String username,
                                           @Query("kat") Category category, @Query("p") Integer page,
                                           @Query("limit") Integer limit, @Query("length") Integer minimumLength);
}
