package me.proxer.library.api.ucp

import me.proxer.library.ProxerCall
import me.proxer.library.entity.ucp.Bookmark
import me.proxer.library.entity.ucp.UcpHistoryEntry
import me.proxer.library.entity.ucp.UcpSettings
import me.proxer.library.entity.ucp.UcpTopTenEntry
import me.proxer.library.entity.user.UserMediaListEntry
import me.proxer.library.enums.Category
import me.proxer.library.enums.MediaLanguage
import me.proxer.library.enums.UcpSettingConstraint
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
    @POST("ucp/deletefavorite")
    fun deleteFavorite(@Field("id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("ucp/deletereminder")
    fun deleteBookmark(@Field("id") id: String?): ProxerCall<Unit>

    @FormUrlEncoded
    @POST("ucp/deletecomment")
    fun deleteComment(@Field("id") id: String?): ProxerCall<Unit>

    @GET("ucp/listsum")
    fun watchedEpisodes(): ProxerCall<Int>

    @GET("ucp/history")
    fun history(
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<UcpHistoryEntry>>

    @GET("ucp/reminder")
    fun bookmarks(
        @Query("kat") category: Category?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("available") available: Boolean?
    ): ProxerCall<List<Bookmark>>

    @GET("ucp/topten")
    fun topTen(): ProxerCall<List<UcpTopTenEntry>>

    @FormUrlEncoded
    @POST("ucp/setreminder")
    fun setBookmark(
        @Field("id") id: String?,
        @Field("episode") episode: Int,
        @Field("language") language: MediaLanguage?,
        @Field("kat") category: Category?
    ): ProxerCall<Unit>

    @GET("ucp/list")
    fun mediaList(
        @Query("kat") category: Category?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") query: String?,
        @Query("search_start") startQuery: String?,
        @Query("filter") filter: UserMediaListFilterType?,
        @Query("sort") sortCriteria: UserMediaListSortCriteria?,
        @Query("isH") includeHentai: Boolean?
    ): ProxerCall<List<UserMediaListEntry>>

    @GET("ucp/settings")
    fun settings(): ProxerCall<UcpSettings>

    @FormUrlEncoded
    @POST("ucp/setsettings")
    fun setSettings(
        @Field("profil") profileVisibility: UcpSettingConstraint?,
        @Field("profil_topten") topTenVisibility: UcpSettingConstraint?,
        @Field("profil_anime") animeVisibility: UcpSettingConstraint?,
        @Field("profil_manga") mangaVisibility: UcpSettingConstraint?,
        @Field("profil_latestcomments") commentVisibility: UcpSettingConstraint?,
        @Field("profil_forum") forumVisibility: UcpSettingConstraint?,
        @Field("profil_connections") friendVisibility: UcpSettingConstraint?,
        @Field("profil_connections_new") friendRequestConstraint: UcpSettingConstraint?,
        @Field("profil_about") aboutVisibility: UcpSettingConstraint?,
        @Field("profil_chronik") historyVisibility: UcpSettingConstraint?,
        @Field("profil_board") guestBookVisibility: UcpSettingConstraint?,
        @Field("profil_board_post") guestBookEntryConstraint: UcpSettingConstraint?,
        @Field("profil_gallery") galleryVisibility: UcpSettingConstraint?,
        @Field("profil_article") articleVisibility: UcpSettingConstraint?,
        @Field("hide_tags") hideTags: Int?,
        @Field("ads_active") showAds: Int?,
        @Field("ads_interval") adInterval: Int?
    ): ProxerCall<List<String>>
}
