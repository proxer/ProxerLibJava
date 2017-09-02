package me.proxer.library.api.anime;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.anime.Stream;
import me.proxer.library.enums.AnimeLanguage;
import retrofit2.http.GET;
import retrofit2.http.Query;

import javax.annotation.ParametersAreNullableByDefault;
import java.util.List;

/**
 * @author Ruben Gees
 */
@ParametersAreNullableByDefault
interface InternalApi {

    @GET("anime/streams")
    ProxerCall<List<Stream>> streams(@Query("id") String id,
                                     @Query("episode") Integer episode,
                                     @Query("language") AnimeLanguage language);

    @GET("anime/proxerstreams")
    ProxerCall<List<Stream>> proxerStreams(@Query("id") String id,
                                           @Query("episode") Integer episode,
                                           @Query("language") AnimeLanguage language);

    @GET("anime/link")
    ProxerCall<String> link(@Query("id") String id);
}
