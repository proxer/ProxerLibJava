package com.proxerme.library.api.info;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.info.Comment;
import com.proxerme.library.entitiy.info.Entry;
import com.proxerme.library.entitiy.info.EntryCore;
import com.proxerme.library.enums.CommentSortCriteria;
import retrofit2.http.GET;
import retrofit2.http.Query;

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
}
