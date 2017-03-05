package com.proxerme.library.api.notifications;

import com.proxerme.library.entitiy.ProxerResponse;
import com.proxerme.library.entitiy.notifications.NewsArticle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

interface InternalApi {

    @GET("notifications/news")
    Call<ProxerResponse<List<NewsArticle>>> news(@Query("p") Integer page, @Query("limit") Integer limit);
}
