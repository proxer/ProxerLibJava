package me.proxer.library.api.list

import retrofit2.Retrofit

/**
 * API for the List class.
 *
 * @author Desnoo.
 */
class ListApi internal constructor(retrofit: Retrofit) {

    private val internalApi = retrofit.create(InternalApi::class.java)

    /**
     * Returns the respective endpoint.
     */
    fun mediaList(): MediaListEndpoint {
        return MediaListEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun mediaSearch(): MediaSearchEndpoint {
        return MediaSearchEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun translatorGroupList(): TranslatorGroupListEndpoint {
        return TranslatorGroupListEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun industryList(): IndustryListEndpoint {
        return IndustryListEndpoint(internalApi)
    }

    /**
     * Returns the respective endpoint.
     */
    fun translatorGroupProjectList(translatorGroupId: String): TranslatorGroupProjectListEndpoint {
        return TranslatorGroupProjectListEndpoint(internalApi, translatorGroupId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun industryProjectList(industryId: String): IndustryProjectListEndpoint {
        return IndustryProjectListEndpoint(internalApi, industryId)
    }

    /**
     * Returns the respective endpoint.
     */
    fun tagList(): TagListEndpoint {
        return TagListEndpoint(internalApi)
    }
}
