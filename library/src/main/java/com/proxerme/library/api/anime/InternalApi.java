package com.proxerme.library.api.anime;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.anime.Stream;
import com.proxerme.library.enums.AnimeLanguage;
import okhttp3.HttpUrl;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @GET("anime/streams")
    ProxerCall<List<Stream>> streams(@Query("id") String id, @Query("episode") Integer episode,
                                     @Query("language") AnimeLanguage language);

    @GET("anime/proxerstreams")
    ProxerCall<List<Stream>> proxerStreams(@Query("id") String id, @Query("episode") Integer episode,
                                           @Query("language") AnimeLanguage language);

    @GET("anime/link")
    ProxerCall<HttpUrl> link(@Query("id") String id);
}
