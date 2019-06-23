package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.entity.info.Comment
import me.proxer.library.entity.info.Entry
import me.proxer.library.entity.info.EntryCore
import me.proxer.library.entity.info.EpisodeInfo
import me.proxer.library.entity.info.ForumDiscussion
import me.proxer.library.entity.info.Industry
import me.proxer.library.entity.info.MediaUserInfo
import me.proxer.library.entity.info.Recommendation
import me.proxer.library.entity.info.Relation
import me.proxer.library.entity.info.TranslatorGroup
import me.proxer.library.enums.CommentSortCriteria
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("info/entry")
    fun entryCore(@Query("id") id: String?): ProxerCall<EntryCore>

    @GET("info/fullentry")
    fun entry(@Query("id") id: String?): ProxerCall<Entry>

    @GET("info/listinfo")
    fun episodeInfo(
        @Query("id") id: String?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("includeNotAvailableChapters") includeNotAvailableChapters: Boolean?
    ): ProxerCall<EpisodeInfo>

    @GET("info/comments")
    fun comments(
        @Query("id") id: String?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("sort") criteria: CommentSortCriteria?
    ): ProxerCall<List<Comment>>

    @GET("info/relations")
    fun relations(
        @Query("id") id: String?,
        @Query("isH") includeHentai: Boolean?
    ): ProxerCall<List<Relation>>

    @GET("info/translatorgroup")
    fun translatorGroup(@Query("id") id: String?): ProxerCall<TranslatorGroup>

    @GET("info/industry")
    fun industry(@Query("id") id: String?): ProxerCall<Industry>

    @FormUrlEncoded
    @POST("info/setuserinfo")
    fun modifyUserInfo(
        @Field("id") id: String?,
        @Field("type") type: UserInfoType?
    ): ProxerCall<Unit>

    @GET("info/recommendations")
    fun recommendations(@Query("id") id: String?): ProxerCall<List<Recommendation>>

    @GET("info/userinfo")
    fun userInfo(@Query("id") id: String?): ProxerCall<MediaUserInfo>

    @GET("info/forum")
    fun forumDiscussions(@Query("id") id: String?): ProxerCall<List<ForumDiscussion>>
}
