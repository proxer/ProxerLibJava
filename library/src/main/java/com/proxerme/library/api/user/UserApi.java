package com.proxerme.library.api.user;

import com.proxerme.library.api.ProxerResponse;
import com.proxerme.library.entitiy.user.ToptenEntry;
import com.proxerme.library.enums.Category;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
public final class UserApi {

    private final InternalApi internalApi;

    public UserApi(@NotNull final Retrofit retrofit) {
        this.internalApi = retrofit.create(InternalApi.class);
    }

    @NotNull
    public ToptenApi topten(@Nullable final String userId, @Nullable final String username) {
        return new ToptenApi(internalApi, userId, username);
    }

    interface InternalApi {

        @GET("user/topten")
        Call<ProxerResponse<List<ToptenEntry>>> topten(@Query("uid") String userId, @Query("username") String username,
                                                       @Query("kat") Category category);
    }
}
