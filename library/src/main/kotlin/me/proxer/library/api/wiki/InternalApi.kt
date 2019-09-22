package me.proxer.library.api.wiki

import me.proxer.library.ProxerCall
import me.proxer.library.entity.wiki.WikiPage
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("wiki/content")
    fun content(@Query("title") title: String): ProxerCall<WikiPage>
}
