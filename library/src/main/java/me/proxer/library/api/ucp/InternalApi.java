package me.proxer.library.api.ucp;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.ucp.Bookmark;
import me.proxer.library.entity.ucp.UcpHistoryEntry;
import me.proxer.library.entity.ucp.UcpSettings;
import me.proxer.library.entity.ucp.UcpTopTenEntry;
import me.proxer.library.entity.user.UserMediaListEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.UcpSettingConstraint;
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
    @POST("ucp/deletefavorite")
    ProxerCall<Void> deleteFavorite(@Field("id") String id);

    @FormUrlEncoded
    @POST("ucp/deletereminder")
    ProxerCall<Void> deleteBookmark(@Field("id") String id);

    @FormUrlEncoded
    @POST("ucp/deletecomment")
    ProxerCall<Void> deleteComment(@Field("id") String id);

    @GET("ucp/listsum")
    ProxerCall<Integer> watchedEpisodes();

    @GET("ucp/history")
    ProxerCall<List<UcpHistoryEntry>> history(@Query("p") Integer page,
                                              @Query("limit") Integer limit);

    @GET("ucp/reminder")
    ProxerCall<List<Bookmark>> bookmarks(@Query("kat") Category category,
                                         @Query("p") Integer page,
                                         @Query("limit") Integer limit,
                                         @Query("available") Boolean available);

    @GET("ucp/topten")
    ProxerCall<List<UcpTopTenEntry>> topTen();

    @FormUrlEncoded
    @POST("ucp/setreminder")
    ProxerCall<Void> setBookmark(@Field("id") String id,
                                 @Field("episode") int episode,
                                 @Field("language") MediaLanguage language,
                                 @Field("kat") Category category);

    @GET("ucp/list")
    ProxerCall<List<UserMediaListEntry>> mediaList(@Query("kat") Category category,
                                                   @Query("p") Integer page,
                                                   @Query("limit") Integer limit,
                                                   @Query("search") String query,
                                                   @Query("search_start") String startQuery,
                                                   @Query("filter") UserMediaListFilterType filter,
                                                   @Query("sort") UserMediaListSortCriteria sortCriteria,
                                                   @Query("isH") Boolean includeHentai);

    @GET("ucp/settings")
    ProxerCall<UcpSettings> settings();

    @FormUrlEncoded
    @POST("ucp/setsettings")
    ProxerCall<Void> setSettings(@Field("profile") UcpSettingConstraint profileVisibility,
                                 @Field("profil_topten") UcpSettingConstraint topTenVisibility,
                                 @Field("profil_anime") UcpSettingConstraint animeVisibility,
                                 @Field("profil_manga") UcpSettingConstraint mangaVisibility,
                                 @Field("profil_latestcomments") UcpSettingConstraint commentVisibility,
                                 @Field("profil_forum") UcpSettingConstraint forumVisibility,
                                 @Field("profil_connections") UcpSettingConstraint friendVisibility,
                                 @Field("profil_connections_new") UcpSettingConstraint friendRequestConstraint,
                                 @Field("profil_about") UcpSettingConstraint aboutVisibility,
                                 @Field("profil_chronik") UcpSettingConstraint historyVisibility,
                                 @Field("profil_board") UcpSettingConstraint guestBookVisibility,
                                 @Field("profil_board_post") UcpSettingConstraint guestBookEntryConstraint,
                                 @Field("profil_gallery") UcpSettingConstraint galleryVisibility,
                                 @Field("profil_article") UcpSettingConstraint articleVisibility,
                                 @Field("hide_tags") Integer hideTags,
                                 @Field("ads_active") Integer showAds,
                                 @Field("ads_interval") Integer adInterval);
}
