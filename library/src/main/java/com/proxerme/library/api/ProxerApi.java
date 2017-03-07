package com.proxerme.library.api;

import com.proxerme.library.api.info.InfoApi;
import com.proxerme.library.api.messenger.MessengerApi;
import com.proxerme.library.api.notifications.NotificationsApi;
import com.proxerme.library.api.user.UserApi;
import com.proxerme.library.util.ProxerUrls;
import com.squareup.moshi.Moshi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.util.List;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ProxerApi {

    private final NotificationsApi notifications;
    private final UserApi user;
    private final InfoApi info;
    private final MessengerApi messenger;

    ProxerApi(@NotNull final Retrofit retrofit) {
        notifications = new NotificationsApi(retrofit);
        user = new UserApi(retrofit);
        info = new InfoApi(retrofit);
        messenger = new MessengerApi(retrofit);
    }

    @NotNull
    public NotificationsApi notifications() {
        return notifications;
    }

    @NotNull
    public UserApi user() {
        return user;
    }

    @NotNull
    public InfoApi info() {
        return info;
    }

    @NotNull
    public MessengerApi messenger() {
        return messenger;
    }

    public static class Builder {

        private static final String DEFAULT_USER_AGENT = "ProxerLibJava/3.0.0";

        private final String apiKey;
        private LoginTokenManager loginTokenManager;
        private String userAgent;

        private Moshi moshi;
        private OkHttpClient okHttp;
        private Retrofit retrofit;

        public Builder(@NotNull final String apiKey) {
            this.apiKey = apiKey;
        }

        @NotNull
        public Builder loginTokenManager(@NotNull final LoginTokenManager loginTokenManager) {
            this.loginTokenManager = loginTokenManager;

            return this;
        }

        @NotNull
        public Builder userAgent(@Nullable final String userAgent) {
            this.userAgent = userAgent;

            return this;
        }

        @NotNull
        public Builder moshi(@Nullable final Moshi moshi) {
            this.moshi = moshi;

            return this;
        }

        @NotNull
        public Builder okHttp(@Nullable final OkHttpClient okHttp) {
            this.okHttp = okHttp;

            return this;
        }

        @NotNull
        public Builder retrofit(@Nullable final Retrofit retrofit) {
            this.retrofit = retrofit;

            return this;
        }

        @NotNull
        public ProxerApi build() {
            initLoginTokenManager();
            initUserAgent();
            initMoshi();
            initOkHttp();
            initRetrofit();

            return new ProxerApi(retrofit);
        }

        private void initLoginTokenManager() {
            if (loginTokenManager == null) {
                loginTokenManager = new DefaultLoginTokenManager();
            }
        }

        private void initUserAgent() {
            if (userAgent == null) {
                userAgent = DEFAULT_USER_AGENT;
            }
        }

        private void initMoshi() {
            final Moshi.Builder builder;

            if (moshi == null) {
                builder = new Moshi.Builder();
            } else {
                builder = moshi.newBuilder();
            }

            moshi = builder
                    .add(new VoidAdapter())
                    .add(new DateAdapter())
                    .add(new BooleanAdapter())
                    .add(new GenreAdapter())
                    .add(new FskConstraintAdapter())
                    .add(new FixRatingDetailsAdapter())
                    .build();
        }

        private void initOkHttp() {
            final OkHttpClient.Builder builder;

            if (okHttp == null) {
                builder = new OkHttpClient.Builder();
            } else {
                builder = okHttp.newBuilder();
            }

            final List<Interceptor> existingInterceptors = builder.interceptors();

            existingInterceptors.add(0, new HeaderInterceptor(apiKey, userAgent));
            existingInterceptors.add(1, new LoginTokenInterceptor(loginTokenManager));

            okHttp = builder.build();
        }

        private void initRetrofit() {
            final Retrofit.Builder builder;

            if (retrofit == null) {
                builder = new Retrofit.Builder();
            } else {
                builder = retrofit.newBuilder();
            }

            retrofit = builder.baseUrl(ProxerUrls.apiBase())
                    .client(okHttp)
                    .addCallAdapterFactory(new ProxerResponseCallAdapterFactory())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addConverterFactory(new EnumRetrofitConverterFactory())
                    .build();
        }
    }
}
