package me.proxer.library.api.list;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.list.*;
import me.proxer.library.enums.*;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
import java.util.Set;

/**
 * @author Desnoo
 */
public interface InternalApi {

    @GET("list/entrylist")
    ProxerCall<List<MediaListEntry>> mediaList(@Query("kat") Category category, @Query("medium") Medium medium,
                                               @Query("isH") Boolean includeHentai, @Query("start") String searchStart,
                                               @Query("sort") MediaListSortCriteria sort,
                                               @Query("sort_type") SortType sortType, @Query("p") Integer page,
                                               @Query("limit") Integer limit);

    @GET("list/entrysearch")
    ProxerCall<List<MediaListEntry>> mediaSearch(@Query("name") String name, @Query("language") Language language,
                                                 @Query("type") MediaType type, @Query("genre") Set<Genre> genres,
                                                 @Query("nogenre") Set<Genre> excludedGenres,
                                                 @Query("fsk") Set<FskConstraint> fskConstraints,
                                                 @Query("sort") MediaSearchSortCriteria sort,
                                                 @Query("length") Integer length,
                                                 @Query("length-limit") LengthBound lengthLimit,
                                                 @Query("tags") String tags, @Query("notags") String excludedTags,
                                                 @Query("tagratefilter") TagRateFilter tagRateFilter,
                                                 @Query("tagspoilerfilter") TagSpoilerFilter tagSpoilerFilter,
                                                 @Query("p") Integer page,
                                                 @Query("limit") Integer limit);

    @GET("list/translatorgroups")
    ProxerCall<List<TranslatorGroupCore>> translatorGroupList(@Query("start") String searchStart,
                                                              @Query("contains") String search,
                                                              @Query("country") Country country,
                                                              @Query("p") Integer page, @Query("limit") Integer limit);

    @GET("list/industrys")
    ProxerCall<List<IndustryCore>> industryList(@Query("start") String searchStart, @Query("contains") String search,
                                                @Query("country") Country country, @Query("p") Integer page,
                                                @Query("limit") Integer limit);

    @GET("list/translatorgroupprojects")
    ProxerCall<List<TranslatorGroupProject>> translatorGroupProjectList(@Query("id") String id,
                                                                        @Query("type") ProjectState projectState,
                                                                        @Query("isH") Boolean includeHentai,
                                                                        @Query("p") Integer page,
                                                                        @Query("limit") Integer limit);

    @GET("list/industryprojects")
    ProxerCall<List<IndustryProject>> industryProjectList(@Query("id") String id, @Query("type") IndustryType type,
                                                          @Query("isH") Boolean includeHentai, @Query("p") Integer page,
                                                          @Query("limit") Integer limit);
}
