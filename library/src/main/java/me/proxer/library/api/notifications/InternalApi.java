package me.proxer.library.api.notifications;

import me.proxer.library.api.ProxerCall;
import me.proxer.library.entitiy.notifications.NewsArticle;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author Ruben Gees
 */
interface InternalApi {

    @GET("notifications/news")
    ProxerCall<List<NewsArticle>> news(@Query("p") Integer page, @Query("limit") Integer limit);
}
