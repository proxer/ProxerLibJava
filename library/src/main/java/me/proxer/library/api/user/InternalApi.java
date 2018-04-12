package me.proxer.library.api.user;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.user.TopTenEntry;
import me.proxer.library.entity.user.User;
import me.proxer.library.entity.user.UserComment;
import me.proxer.library.entity.user.UserHistoryEntry;
import me.proxer.library.entity.user.UserInfo;
import me.proxer.library.entity.user.UserMediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.UserMediaListFilterType;
import me.proxer.library.enums.UserMediaListSortCriteria;
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
interface InternalApi {

    @FormUrlEncoded
    @POST("user/login")
    ProxerCall<User> login(@Field("username") String username,
                           @Field("password") String password,
                           @Field("secretkey") String secretKey);

    @POST("user/logout")
    ProxerCall<Void> logout();

    @GET("user/topten")
    ProxerCall<List<TopTenEntry>> topTen(@Query("uid") String userId,
                                         @Query("username") String username,
                                         @Query("kat") Category category,
                                         @Query("isH") Boolean includeHentai);

    @GET("user/userinfo")
    ProxerCall<UserInfo> userInfo(@Query("uid") String userId,
                                  @Query("username") String username);

    @GET("user/list")
    ProxerCall<List<UserMediaListEntry>> userMediaList(@Query("uid") String userId,
                                                       @Query("username") String username,
                                                       @Query("kat") Category category,
                                                       @Query("p") Integer page,
                                                       @Query("limit") Integer limit,
                                                       @Query("search") String query,
                                                       @Query("search_start") String startQuery,
                                                       @Query("filter") UserMediaListFilterType filter,
                                                       @Query("sort") UserMediaListSortCriteria sortCriteria,
                                                       @Query("isH") Boolean includeHentai);

    @GET("user/comments")
    ProxerCall<List<UserComment>> comments(@Query("uid") String userId,
                                           @Query("username") String username,
                                           @Query("kat") Category category,
                                           @Query("p") Integer page,
                                           @Query("limit") Integer limit,
                                           @Query("length") Integer minimumLength);

    @GET("user/history")
    ProxerCall<List<UserHistoryEntry>> history(@Query("uid") String userId,
                                               @Query("username") String username,
                                               @Query("p") Integer page,
                                               @Query("limit") Integer limit,
                                               @Query("isH") Boolean includeHentai);
}
