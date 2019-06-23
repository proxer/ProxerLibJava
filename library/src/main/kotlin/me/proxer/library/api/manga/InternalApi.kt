package me.proxer.library.api.manga

import me.proxer.library.ProxerCall
import me.proxer.library.entity.manga.Chapter
import me.proxer.library.enums.Language
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
internal interface InternalApi {

    @GET("manga/chapter")
    fun chapter(
        @Query("id") id: String?,
        @Query("episode") episode: Int?,
        @Query("language") language: Language?
    ): ProxerCall<Chapter>
}
