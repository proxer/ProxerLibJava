package me.proxer.library.api.user

import me.proxer.library.ProxerCall
import me.proxer.library.entity.user.TopTenEntry
import me.proxer.library.entity.user.User
import me.proxer.library.entity.user.UserAbout
import me.proxer.library.entity.user.UserComment
import me.proxer.library.entity.user.UserHistoryEntry
import me.proxer.library.entity.user.UserInfo
import me.proxer.library.entity.user.UserMediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.UserMediaListFilterType
import me.proxer.library.enums.UserMediaListSortCriteria
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("secretkey") secretKey: String?
    ): ProxerCall<User>

    @POST("user/logout")
    fun logout(): ProxerCall<Unit>

    @GET("user/topten")
    fun topTen(
        @Query("uid") userId: String?,
        @Query("username") username: String?,
        @Query("kat") category: Category?,
        @Query("isH") includeHentai: Boolean?
    ): ProxerCall<List<TopTenEntry>>

    @GET("user/userinfo")
    fun userInfo(
        @Query("uid") userId: String?,
        @Query("username") username: String?
    ): ProxerCall<UserInfo>

    @GET("user/about")
    fun userAbout(
        @Query("uid") userId: String?,
        @Query("username") username: String?
    ): ProxerCall<UserAbout>

    @GET("user/list")
    fun userMediaList(
        @Query("uid") userId: String?,
        @Query("username") username: String?,
        @Query("kat") category: Category?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") query: String?,
        @Query("search_start") startQuery: String?,
        @Query("filter") filter: UserMediaListFilterType?,
        @Query("sort") sortCriteria: UserMediaListSortCriteria?,
        @Query("isH") includeHentai: Boolean?
    ): ProxerCall<List<UserMediaListEntry>>

    @GET("user/comments")
    fun comments(
        @Query("uid") userId: String?,
        @Query("username") username: String?,
        @Query("kat") category: Category?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("length") minimumLength: Int?,
        @Query("state") state: String?,
        @Query("has") has: String?
    ): ProxerCall<List<UserComment>>

    @GET("user/history")
    fun history(
        @Query("uid") userId: String?,
        @Query("username") username: String?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("isH") includeHentai: Boolean?
    ): ProxerCall<List<UserHistoryEntry>>
}
