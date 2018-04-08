package me.proxer.library.api;

import com.serjltt.moshi.adapters.FallbackEnum;
import com.squareup.moshi.Moshi;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.BuildConfig;
import me.proxer.library.api.anime.AnimeApi;
import me.proxer.library.api.apps.AppsApi;
import me.proxer.library.api.forum.ForumApi;
import me.proxer.library.api.info.InfoApi;
import me.proxer.library.api.list.ListApi;
import me.proxer.library.api.manga.MangaApi;
import me.proxer.library.api.media.MediaApi;
import me.proxer.library.api.messenger.MessengerApi;
import me.proxer.library.api.notifications.NotificationsApi;
import me.proxer.library.api.ucp.UcpApi;
import me.proxer.library.api.user.UserApi;
import me.proxer.library.util.ProxerUrls;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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
@Getter
@Accessors(fluent = true)
public final class ProxerApi {

    /**
     * Returns the Moshi instance.
     */
    private final Moshi moshi;

    /**
     * Returns the OkHttpClient instance.
     */
    private final OkHttpClient client;

    /**
     * Returns the Retrofit instance.
     */
    private final Retrofit retrofit;

    /**
     * Returns the respective API.
     */
    private final NotificationsApi notifications;

    /**
     * Returns the respective API.
     */
    private final UserApi user;

    /**
     * Returns the respective API.
     */
    private final InfoApi info;

    /**
     * Returns the respective API.
     */
    private final MessengerApi messenger;

    /**
     * Returns the respective API.
     */
    private final ListApi list;

    /**
     * Returns the respective API.
     */
    private final UcpApi ucp;

    /**
     * Returns the respective API.
     */
    private final AnimeApi anime;

    /**
     * Returns the respective API.
     */
    private final MangaApi manga;

    /**
     * Returns the respective API.
     */
    private final ForumApi forum;

    /**
     * Returns the respective API.
     */
    private final MediaApi media;

    /**
     * Returns the respective API.
     */
    private final AppsApi apps;

    private ProxerApi(final Moshi moshi, final OkHttpClient client, final Retrofit retrofit) {
        this.moshi = moshi;
        this.client = client;
        this.retrofit = retrofit;

        notifications = new NotificationsApi(retrofit);
        user = new UserApi(retrofit);
        info = new InfoApi(retrofit);
        messenger = new MessengerApi(retrofit);
        list = new ListApi(retrofit);
        ucp = new UcpApi(retrofit);
        anime = new AnimeApi(retrofit);
        manga = new MangaApi(retrofit);
        forum = new ForumApi(retrofit);
        media = new MediaApi(retrofit);
        apps = new AppsApi(retrofit);
    }

    /**
     * Builder class for the API.
     * <p>
     * You can set customized instances, of the internally used libraries: Moshi, OkHttp and Retrofit.
     * Moreover you can specify your own {@link LoginTokenManager} and user agent.
     */
    @Accessors(fluent = true)
    public static final class Builder {

        private static final String DEFAULT_USER_AGENT = "ProxerLibJava/" + BuildConfig.VERSION;

        private final String apiKey;

        /**
         * Sets a custom login token manager.
         */
        @Setter
        private LoginTokenManager loginTokenManager;

        /**
         * Sets a custom user agent.
         * <p>
         * If not set, it will default to "ProxerLibJava/[version]"
         */
        @Setter
        private String userAgent;

        /**
         * Sets a custom Moshi instance.
         * <p>
         * Note, that a internally, a new instance will be constructed, with the adjustments included, you did on your
         * instance.
         */
        @Setter
        private Moshi moshi;

        /**
         * Sets a custom OkHttp instance.
         * <p>
         * Note, that a internally, a new instance will be constructed, with the adjustments included, you did on your
         * instance.
         */
        @Setter
        private OkHttpClient client;

        /**
         * Sets a custom Retrofit instance.
         * <p>
         * Note, that a internally, a new instance will be constructed, with the adjustments included, you did on your
         * instance.
         */
        @Setter
        private Retrofit retrofit;

        /**
         * Sets the logging strategy. You can either disable logging, enable it for everything concerning the api, or
         * for all traffic, sent through the OkHttp instance.
         */
        @Setter
        private LoggingStrategy loggingStrategy = LoggingStrategy.NONE;

        /**
         * Sets the tag to use for logs.
         */
        @Setter
        private String loggingTag = "ProxerLibJava";

        /**
         * Constructs a new instance of the builder, with the passed {@code apiKey}.
         */
        public Builder(final String apiKey) {
            this.apiKey = apiKey;
        }

        /**
         * Finally builds the {@link ProxerApi} with the provided adjustments.
         */
        public ProxerApi build() {
            initLoginTokenManager();
            initUserAgent();
            initMoshi();
            initClient();
            initRetrofit();

            return new ProxerApi(moshi, client, retrofit);
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
                    .add(new PageAdapter())
                    .add(new BooleanAdapter())
                    .add(new HttpUrlAdapter())
                    .add(new ConferenceAdapter())
                    .add(new EpisodeInfoAdapter())
                    .add(new AdaptionInfoAdapter())
                    .add(new NotificationAdapter())
                    .add(new ConferenceInfoAdapter())
                    .add(new PrimitiveBooleanAdapter())
                    .add(new NotificationInfoAdapter())
                    .add(new FixRatingDetailsAdapter())
                    .add(new DelimitedEnumSetAdapterFactory())
                    .add(FallbackEnum.ADAPTER_FACTORY)
                    .build();
        }

        private void initClient() {
            final OkHttpClient.Builder builder;

            if (client == null) {
                builder = new OkHttpClient.Builder();
            } else {
                builder = client.newBuilder();
            }

            if (loggingStrategy != LoggingStrategy.NONE) {
                builder.addInterceptor(new LoggingInterceptor(loggingStrategy, loggingTag));
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

        /**
         * Enum with the available strategies for http logging.
         */
        public enum LoggingStrategy {

            /**
             * Nothing shall be logged.
             */
            NONE,

            /**
             * Everything concerning the api shall be logged.
             */
            API,

            /**
             * All network traffic shall be logged.
             */
            ALL
        }
    }
}
