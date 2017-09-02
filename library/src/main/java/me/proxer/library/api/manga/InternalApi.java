package me.proxer.library.api.manga;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.manga.Chapter;
import me.proxer.library.enums.Language;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.annotation.ParametersAreNullableByDefault;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
interface InternalApi {

    @GET("manga/chapter")
    ProxerCall<Chapter> chapter(@Query("id") String id,
                                @Query("episode") Integer episode,
                                @Query("language") Language language);
}
