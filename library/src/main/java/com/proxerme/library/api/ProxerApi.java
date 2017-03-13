package com.proxerme.library.api;

import com.proxerme.library.api.anime.AnimeApi;
import com.proxerme.library.api.info.InfoApi;
import com.proxerme.library.api.list.ListApi;
import com.proxerme.library.api.messenger.MessengerApi;
import com.proxerme.library.api.notifications.NotificationsApi;
import com.proxerme.library.api.user.UserApi;
import com.proxerme.library.util.ProxerUrls;
import com.squareup.moshi.Moshi;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.util.List;

/**
 * Class for access to the various APIs of Proxer.
 * <p>
 * Before usage, you have to construct an instance through the {@link Builder}.
 * <p>
 * Note, that this is not a singleton. Each instance is separated from each other; You could even have instances with a
 * different api key.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class ProxerApi {

    /**
     * Returns the respective API.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final NotificationsApi notifications;

    /**
     * Returns the respective API.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final UserApi user;

    /**
     * Returns the respective API.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final InfoApi info;

    /**
     * Returns the respective API.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final MessengerApi messenger;

    /**
     * Returns the respective API.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final ListApi list;

    /**
     * Returns the respective API.
     */
    @Getter(onMethod = @__({@NotNull}))
    private final AnimeApi anime;

    ProxerApi(@NotNull final Retrofit retrofit) {
        notifications = new NotificationsApi(retrofit);
        user = new UserApi(retrofit);
        info = new InfoApi(retrofit);
        messenger = new MessengerApi(retrofit);
        list = new ListApi(retrofit);
        anime = new AnimeApi(retrofit);
    }

    /**
     * Builder class for the API.
     * <p>
     * You can set customized instances, of the internally used libraries: Moshi, OkHttp and Retrofit.
     * Moreover you can specify your own {@link LoginTokenManager} and user agent.
     */
    @Accessors(fluent = true)
    public static class Builder {

        private static final String DEFAULT_USER_AGENT = "ProxerLibJava/3.0.0";

        private final String apiKey;

        /**
         * Sets a custom login token manager.
         */
        @Nullable
        @Setter(onMethod = @__({@NotNull}))
        private LoginTokenManager loginTokenManager;

        /**
         * Sets a custom user agent.
         * <p>
         * If not set, it will default to "ProxerLibJava/[version]"
         */
        @Nullable
        @Setter(onMethod = @__({@NotNull}))
        private String userAgent;

        /**
         * Sets a custom Moshi instance.
         * <p>
         * Note, that a internally, a new instance will be constructed, with the adjustments included, you did on your
         * instance.
         */
        @Nullable
        @Setter(onMethod = @__({@NotNull}))
        private Moshi moshi;

        /**
         * Sets a custom OkHttp instance.
         * <p>
         * Note, that a internally, a new instance will be constructed, with the adjustments included, you did on your
         * instance.
         */
        @Nullable
        @Setter(onMethod = @__({@NotNull}))
        private OkHttpClient client;

        /**
         * Sets a custom Retrofit instance.
         * <p>
         * Note, that a internally, a new instance will be constructed, with the adjustments included, you did on your
         * instance.
         */
        @Nullable
        @Setter(onMethod = @__({@NotNull}))
        private Retrofit retrofit;

        /**
         * Constructs a new instance of the builder, with the passed {@code apiKey}.
         */
        public Builder(@NotNull final String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * Finally builds the {@link ProxerApi} with the provided adjustments.
         */
        @NotNull
        public ProxerApi build() {
            initLoginTokenManager();
            initUserAgent();
            initMoshi();
            initClient();
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
                    .add(new DelimitedEnumSetAdapterFactory())
                    .add(new HttpUrlAdapter())
                    .add(new ConferenceAdapter())
                    .add(new ConferenceInfoAdapter())
                    .add(new FixRatingDetailsAdapter())
                    .build();
        }

        private void initClient() {
            final OkHttpClient.Builder builder;

            if (client == null) {
                builder = new OkHttpClient.Builder();
            } else {
                builder = client.newBuilder();
            }

            final List<Interceptor> existingInterceptors = builder.interceptors();

            existingInterceptors.add(0, new HeaderInterceptor(apiKey, userAgent));
            existingInterceptors.add(1, new LoginTokenInterceptor(loginTokenManager));

            client = builder.build();
        }

        private void initRetrofit() {
            final Retrofit.Builder builder;

            if (retrofit == null) {
                builder = new Retrofit.Builder();
            } else {
                builder = retrofit.newBuilder();
            }

            retrofit = builder.baseUrl(ProxerUrls.apiBase())
                    .client(client)
                    .addCallAdapterFactory(new ProxerResponseCallAdapterFactory())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addConverterFactory(new EnumRetrofitConverterFactory())
                    .build();
        }
    }
}
