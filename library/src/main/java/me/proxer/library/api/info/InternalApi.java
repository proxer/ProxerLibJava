package me.proxer.library.api.info;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.info.Comment;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.entity.info.EntryCore;
import me.proxer.library.entity.info.EpisodeInfo;
import me.proxer.library.entity.info.Industry;
import me.proxer.library.entity.info.MediaUserInfo;
import me.proxer.library.entity.info.Recommendation;
import me.proxer.library.entity.info.Relation;
import me.proxer.library.entity.info.TranslatorGroup;
import me.proxer.library.enums.CommentSortCriteria;
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

    @GET("info/entry")
    ProxerCall<EntryCore> entryCore(@Query("id") String id);

    @GET("info/fullentry")
    ProxerCall<Entry> entry(@Query("id") String id);

    @GET("info/listinfo")
    ProxerCall<EpisodeInfo> episodeInfo(@Query("id") String id,
                                        @Query("p") Integer page,
                                        @Query("limit") Integer limit);

    @GET("info/comments")
    ProxerCall<List<Comment>> comments(@Query("id") String id,
                                       @Query("p") Integer page,
                                       @Query("limit") Integer limit,
                                       @Query("sort") CommentSortCriteria criteria);

    @GET("info/relations")
    ProxerCall<List<Relation>> relations(@Query("id") String id,
                                         @Query("isH") Boolean includeHentai);

    @GET("info/translatorgroup")
    ProxerCall<TranslatorGroup> translatorGroup(@Query("id") String id);

    @GET("info/industry")
    ProxerCall<Industry> industry(@Query("id") String id);

    @FormUrlEncoded
    @POST("info/setuserinfo")
    ProxerCall<Void> modifyUserInfo(@Field("id") String id,
                                    @Field("type") UserInfoType type);

    @GET("info/recommendations")
    ProxerCall<List<Recommendation>> recommendations(@Query("id") String id);

    @GET("info/userinfo")
    ProxerCall<MediaUserInfo> userInfo(@Query("id") String id);
}
