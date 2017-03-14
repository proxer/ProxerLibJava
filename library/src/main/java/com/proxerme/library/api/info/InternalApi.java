package com.proxerme.library.api.info;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.*;
import com.proxerme.library.enums.CommentSortCriteria;
import com.proxerme.library.enums.UserInfoType;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @GET("info/comments")
    ProxerCall<List<Comment>> comments(@Query("id") String id, @Query("p") Integer page, @Query("limit") Integer limit,
                                       @Query("sort") CommentSortCriteria criteria);

    @GET("info/entry")
    ProxerCall<EntryCore> entryCore(@Query("id") String id);

    @GET("info/fullentry")
    ProxerCall<Entry> entry(@Query("id") String id);

    @GET("info/names")
    ProxerCall<List<Synonym>> synonyms(@Query("id") String id);

    @GET("info/translatorgroup")
    ProxerCall<TranslatorGroup> translatorGroup(@Query("id") String id);

    @GET("info/industry")
    ProxerCall<Industry> industry(@Query("id") String id);

    @FormUrlEncoded
    @POST("info/setuserinfo")
    ProxerCall<Void> modifyUserInfo(@Field("id") String id, @Field("type") UserInfoType type);
}
