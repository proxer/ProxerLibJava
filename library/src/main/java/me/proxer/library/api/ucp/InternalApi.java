package me.proxer.library.api.ucp;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.ucp.Bookmark;
import me.proxer.library.entitiy.ucp.UcpHistoryEntry;
import me.proxer.library.entitiy.ucp.UcpTopTenEntry;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.MediaLanguage;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @FormUrlEncoded
    @POST("ucp/deletefavorite")
    ProxerCall<Void> deleteFavorite(@Field("id") String id);

    @FormUrlEncoded
    @POST("ucp/deletereminder")
    ProxerCall<Void> deleteBookmark(@Field("id") String id);

    @GET("ucp/listsum")
    ProxerCall<Integer> watchedEpisodes();

    @GET("ucp/history")
    ProxerCall<List<UcpHistoryEntry>> history(@Query("p") Integer page, @Query("limit") Integer limit);

    @GET("ucp/reminder")
    ProxerCall<List<Bookmark>> bookmarks(@Query("kat") Category category, @Query("p") Integer page,
                                         @Query("limit") Integer limit);

    @GET("ucp/topten")
    ProxerCall<List<UcpTopTenEntry>> topTen();

    @FormUrlEncoded
    @POST("ucp/setreminder")
    ProxerCall<Void> setBookmark(@Field("id") String id, @Field("episode") int episode,
                                 @Field("language") MediaLanguage language, @Field("kat") Category category);
}
