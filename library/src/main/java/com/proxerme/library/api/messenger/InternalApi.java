package com.proxerme.library.api.messenger;

import com.proxerme.library.api.ProxerCall;
import com.proxerme.library.entitiy.messenger.Conference;
import com.proxerme.library.enums.ConferenceType;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

interface InternalApi {

    @GET("messenger/conferences")
    ProxerCall<List<Conference>> conferences(@Query("p") Integer page, @Query("type") ConferenceType type);
}
