package com.proxerme.library.api.list;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.list.MediaEntry;
import com.proxerme.library.enums.*;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * TODO: Describe interface
 *
 * @author Desnoo
 */
public interface InternalApi {

    @GET("list/entrylist")
    ProxerCall<List<MediaEntry>> mediaEntries(@Query("kat") Category category, @Query("medium") Medium medium,
                                              @Query("isH") Boolean hentai, @Query("start") String startSearchString,
                                              @Query("p") Integer page, @Query("limit") Integer limit);

    @GET("list/entrysearch")
    ProxerCall<List<MediaEntry>> searchEntries(@Query("name") String name, @Query("language") Language language,
                                               @Query("type") MediaType type, @Query("genre") String genre,
                                               @Query("nogenre") String noGenre, @Query("fsk") String fsk,
                                               @Query("sort") String sort, @Query("length") Integer length,
                                               @Query("length-limit") String lengthLimit, @Query("tags") String tags,
                                               @Query("notags") String noTags, @Query("tagratefilter") String tagRateFilter,
                                               @Query("tagspoilerfilter") String tagSpoilerFilter, @Query("p") Integer page,
                                               @Query("limit") Integer limit);

    /*
    * TODO: implement
    * @GET("list/tagids")
    * ProxerCall<>
    * @GET("list/tags")
    * ProxerCall<>
    * @GET("list/translatorgroups")
    * ProxerCall<>
    * @GET("list/industrys")
    * ProxerCall<>
    * @GET("list/translatorgroupprojects")
    * ProxerCall<>
    * @GET("list/industryprojects")
    * ProxerCall<>
    */

}
