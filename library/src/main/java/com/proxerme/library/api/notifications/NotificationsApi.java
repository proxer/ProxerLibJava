package com.proxerme.library.api.notifications;

import com.proxerme.library.api.ProxerResponse;
import com.proxerme.library.entitiy.notifications.NewsArticle;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class NotificationsApi {

    private InternalApi internalApi;

    public NotificationsApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public NewsApi news() {
        return new NewsApi(internalApi);
    }

    interface InternalApi {

        @GET("notifications/news")
        Call<ProxerResponse<List<NewsArticle>>> news(@Query("p") Integer page, @Query("limit") Integer limit);
    }
}
