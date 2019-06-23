package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.entity.list.IndustryCore
import me.proxer.library.entity.list.IndustryProject
import me.proxer.library.entity.list.MediaListEntry
import me.proxer.library.entity.list.Tag
import me.proxer.library.entity.list.TranslatorGroupCore
import me.proxer.library.entity.list.TranslatorGroupProject
import me.proxer.library.enums.Category
import me.proxer.library.enums.Country
import me.proxer.library.enums.IndustryType
import me.proxer.library.enums.Language
import me.proxer.library.enums.LengthBound
import me.proxer.library.enums.MediaListSortCriteria
import me.proxer.library.enums.MediaSearchSortCriteria
import me.proxer.library.enums.MediaType
import me.proxer.library.enums.Medium
import me.proxer.library.enums.ProjectState
import me.proxer.library.enums.SortType
import me.proxer.library.enums.TagRateFilter
import me.proxer.library.enums.TagSortCriteria
import me.proxer.library.enums.TagSpoilerFilter
import me.proxer.library.enums.TagSubType
import me.proxer.library.enums.TagType
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Desnoo
 */
internal interface InternalApi {

    @GET("list/entrylist")
    fun mediaList(
        @Query("kat") category: Category?,
        @Query("medium") medium: Medium?,
        @Query("isH") includeHentai: Boolean?,
        @Query("start") searchStart: String?,
        @Query("sort") sort: MediaListSortCriteria?,
        @Query("sort_type") sortType: SortType?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<MediaListEntry>>

    @GET("list/entrysearch")
    fun mediaSearch(
        @Query("name") name: String?,
        @Query("language") language: Language?,
        @Query("type") type: MediaType?,
        @Query("fsk") fskConstraints: String?,
        @Query("sort") sort: MediaSearchSortCriteria?,
        @Query("length") length: Int?,
        @Query("length-limit") lengthLimit: LengthBound?,
        @Query("tags") tags: String?,
        @Query("notags") excludedTags: String?,
        @Query("taggenre") genres: String?,
        @Query("notaggenre") excludedGenres: String?,
        @Query("tagratefilter") tagRateFilter: TagRateFilter?,
        @Query("tagspoilerfilter") tagSpoilerFilter: TagSpoilerFilter?,
        @Query("hide_finished") hideFinished: Int?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<MediaListEntry>>

    @GET("list/translatorgroups")
    fun translatorGroupList(
        @Query("start") searchStart: String?,
        @Query("contains") search: String?,
        @Query("country") country: Country?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<TranslatorGroupCore>>

    @GET("list/industrys")
    fun industryList(
        @Query("start") searchStart: String?,
        @Query("contains") search: String?,
        @Query("country") country: Country?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<IndustryCore>>

    @GET("list/translatorgroupprojects")
    fun translatorGroupProjectList(
        @Query("id") id: String?,
        @Query("type") projectState: ProjectState?,
        @Query("isH") includeHentai: Int?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<TranslatorGroupProject>>

    @GET("list/industryprojects")
    fun industryProjectList(
        @Query("id") id: String?,
        @Query("type") type: IndustryType?,
        @Query("isH") includeHentai: Int?,
        @Query("p") page: Int?,
        @Query("limit") limit: Int?
    ): ProxerCall<List<IndustryProject>>

    @GET("list/tags")
    fun tagList(
        @Query("search") search: String?,
        @Query("type") type: TagType?,
        @Query("sort") sort: TagSortCriteria?,
        @Query("sort_type") sortType: TagSortType?,
        @Query("subtype") subType: TagSubType?
    ): ProxerCall<List<Tag>>
}
