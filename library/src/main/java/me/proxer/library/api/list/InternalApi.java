package me.proxer.library.api.list;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.list.IndustryCore;
import me.proxer.library.entity.list.IndustryProject;
import me.proxer.library.entity.list.MediaListEntry;
import me.proxer.library.entity.list.Tag;
import me.proxer.library.entity.list.TranslatorGroupCore;
import me.proxer.library.entity.list.TranslatorGroupProject;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.IndustryType;
import me.proxer.library.enums.Language;
import me.proxer.library.enums.LengthBound;
import me.proxer.library.enums.MediaListSortCriteria;
import me.proxer.library.enums.MediaSearchSortCriteria;
import me.proxer.library.enums.MediaType;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.ProjectState;
import me.proxer.library.enums.SortType;
import me.proxer.library.enums.TagRateFilter;
import me.proxer.library.enums.TagSortCriteria;
import me.proxer.library.enums.TagSpoilerFilter;
import me.proxer.library.enums.TagSubType;
import me.proxer.library.enums.TagType;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Desnoo
 */
@ParametersAreNullableByDefault
interface InternalApi {

    @GET("list/entrylist")
    ProxerCall<List<MediaListEntry>> mediaList(@Query("kat") Category category,
                                               @Query("medium") Medium medium,
                                               @Query("isH") Boolean includeHentai,
                                               @Query("start") String searchStart,
                                               @Query("sort") MediaListSortCriteria sort,
                                               @Query("sort_type") SortType sortType,
                                               @Query("p") Integer page,
                                               @Query("limit") Integer limit);

    @GET("list/entrysearch")
    ProxerCall<List<MediaListEntry>> mediaSearch(@Query("name") String name,
                                                 @Query("language") Language language,
                                                 @Query("type") MediaType type,
                                                 @Query("genre") String genres,
                                                 @Query("nogenre") String excludedGenres,
                                                 @Query("fsk") String fskConstraints,
                                                 @Query("sort") MediaSearchSortCriteria sort,
                                                 @Query("length") Integer length,
                                                 @Query("length-limit") LengthBound lengthLimit,
                                                 @Query("tags") String tags,
                                                 @Query("notags") String excludedTags,
                                                 @Query("taggenre") String tagGenres,
                                                 @Query("notaggenre") String excludedTagGenres,
                                                 @Query("tagratefilter") TagRateFilter tagRateFilter,
                                                 @Query("tagspoilerfilter") TagSpoilerFilter tagSpoilerFilter,
                                                 @Query("p") Integer page,
                                                 @Query("limit") Integer limit);

    @GET("list/translatorgroups")
    ProxerCall<List<TranslatorGroupCore>> translatorGroupList(@Query("start") String searchStart,
                                                              @Query("contains") String search,
                                                              @Query("country") Country country,
                                                              @Query("p") Integer page,
                                                              @Query("limit") Integer limit);

    @GET("list/industrys")
    ProxerCall<List<IndustryCore>> industryList(@Query("start") String searchStart,
                                                @Query("contains") String search,
                                                @Query("country") Country country,
                                                @Query("p") Integer page,
                                                @Query("limit") Integer limit);

    @GET("list/translatorgroupprojects")
    ProxerCall<List<TranslatorGroupProject>> translatorGroupProjectList(@Query("id") String id,
                                                                        @Query("type") ProjectState projectState,
                                                                        @Query("isH") Integer includeHentai,
                                                                        @Query("p") Integer page,
                                                                        @Query("limit") Integer limit);

    @GET("list/industryprojects")
    ProxerCall<List<IndustryProject>> industryProjectList(@Query("id") String id,
                                                          @Query("type") IndustryType type,
                                                          @Query("isH") Integer includeHentai,
                                                          @Query("p") Integer page,
                                                          @Query("limit") Integer limit);

    @GET("list/tags")
    ProxerCall<List<Tag>> tagList(@Query("search") String search,
                                  @Query("type") TagType type,
                                  @Query("sort") TagSortCriteria sort,
                                  @Query("sort_type") TagSortType sortType,
                                  @Query("subtype") TagSubType subType);
}
