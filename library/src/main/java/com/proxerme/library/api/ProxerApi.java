package com.proxerme.library.api;

import com.proxerme.library.api.notifications.NotificationsApi;
import com.proxerme.library.api.user.UserApi;
import com.proxerme.library.util.ProxerUrls;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.Json;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ProxerApi {

    private final NotificationsApi notifications;
    private final UserApi user;

    ProxerApi(@NotNull final Retrofit retrofit) {
        notifications = new NotificationsApi(retrofit);
        user = new UserApi(retrofit);
    }

    @NotNull
    public NotificationsApi notifications() {
        return notifications;
    }

    @NotNull
    public UserApi user() {
        return user;
    }

    public static class Builder {

        private final String apiKey;
        private String userAgent = "ProxerLibAndroid/3.0.0";

        private Moshi moshi;
        private OkHttpClient okHttp;
        private Retrofit retrofit;

        public Builder(@NotNull final String apiKey) {
            this.apiKey = apiKey;
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
            initMoshi();
            initOkHttp();
            initRetrofit();

            return new ProxerApi(retrofit);
        }

        private void initMoshi() {
            final Moshi.Builder builder;

            if (moshi == null) {
                builder = new Moshi.Builder();
            } else {
                builder = moshi.newBuilder();
            }

            moshi = builder
                    .add(new TimestampDateAdapter())
                    .build();
        }

        private void initOkHttp() {
            final OkHttpClient.Builder builder;

            if (okHttp == null) {
                builder = new OkHttpClient.Builder();
            } else {
                builder = okHttp.newBuilder();
            }

            okHttp = builder
                    .addInterceptor(new HeaderInterceptor(apiKey, userAgent))
                    .build();
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
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addConverterFactory(new EnumRetrofitConverterFactory())
                    .build();
        }

        private static final class TimestampDateAdapter {
            @FromJson
            Date fromJson(final long timestamp) {
                // The API returns seconds
                return new Date(timestamp * 1000);
            }

            @ToJson
            String toJson(final Date date) {
                return String.valueOf(date.getTime());
            }
        }

        private static final class HeaderInterceptor implements Interceptor {

            private static final String API_KEY_HEADER = "proxer-api-key";
            private static final String HEADER_USER_AGENT = "User-Agent";

            private final String apiKey;
            private final String userAgent;

            HeaderInterceptor(@NotNull final String apiKey, @Nullable final String userAgent) {
                this.apiKey = apiKey;
                this.userAgent = userAgent;
            }

            @Override
            public Response intercept(final Chain chain) throws IOException {
                final Request oldRequest = chain.request();

                if (oldRequest.url().host().equals(ProxerUrls.apiBase().host())) {
                    final Request.Builder newRequestBuilder = chain.request().newBuilder()
                            .addHeader(API_KEY_HEADER, apiKey);

                    if (userAgent != null && oldRequest.header(HEADER_USER_AGENT) == null) {
                        newRequestBuilder.addHeader(HEADER_USER_AGENT, userAgent);
                    }

                    return chain.proceed(newRequestBuilder.build());
                }

                return chain.proceed(oldRequest);
            }
        }

        private static final class EnumRetrofitConverterFactory extends Converter.Factory {
            @Override
            public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                if (type instanceof Class && ((Class<?>) type).isEnum()) {
                    return new Converter<Enum<?>, String>() {
                        @Override
                        public String convert(final Enum<?> e) throws IOException {
                            try {
                                return e.getClass().getField(e.name()).getAnnotation(Json.class).name();
                            } catch (NoSuchFieldException ignored) {
                                return null;
                            }
                        }
                    };
                }

                return null;
            }
        }
    }
}
