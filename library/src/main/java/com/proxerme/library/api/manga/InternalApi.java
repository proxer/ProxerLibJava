package com.proxerme.library.api.manga;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.manga.Chapter;
import com.proxerme.library.enums.Language;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @GET("manga/chapter")
    ProxerCall<Chapter> chapter(@Query("id") String id, @Query("episode") Integer episode,
                                @Query("language") Language language);
}
