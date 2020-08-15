package me.proxer.library

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import me.proxer.library.ProxerApi.Builder
import me.proxer.library.api.anime.AnimeApi
import me.proxer.library.api.apps.AppsApi
import me.proxer.library.api.chat.ChatApi
import me.proxer.library.api.comment.CommentApi
import me.proxer.library.api.forum.ForumApi
import me.proxer.library.api.info.InfoApi
import me.proxer.library.api.list.ListApi
import me.proxer.library.api.manga.MangaApi
import me.proxer.library.api.media.MediaApi
import me.proxer.library.api.messenger.MessengerApi
import me.proxer.library.api.notifications.NotificationsApi
import me.proxer.library.api.ucp.UcpApi
import me.proxer.library.api.user.UserApi
import me.proxer.library.api.users.UsersApi
import me.proxer.library.api.wiki.WikiApi
import me.proxer.library.internal.DefaultLoginTokenManager
import me.proxer.library.internal.adapter.BooleanAdapterFactory
import me.proxer.library.internal.adapter.ConferenceAdapter
import me.proxer.library.internal.adapter.ConferenceInfoAdapter
import me.proxer.library.internal.adapter.DateAdapter
import me.proxer.library.internal.adapter.DelimitedEnumSetAdapterFactory
import me.proxer.library.internal.adapter.DelimitedStringSetAdapterFactory
import me.proxer.library.internal.adapter.EnumRetrofitConverterFactory
import me.proxer.library.internal.adapter.EpisodeInfoAdapter
import me.proxer.library.internal.adapter.FixRatingDetailsAdapter
import me.proxer.library.internal.adapter.HttpUrlAdapter
import me.proxer.library.internal.adapter.NotificationAdapter
import me.proxer.library.internal.adapter.NotificationInfoAdapter
import me.proxer.library.internal.adapter.PageAdapter
import me.proxer.library.internal.adapter.ProxerResponseCallAdapterFactory
import me.proxer.library.internal.adapter.UcpSettingConstraintAdapter
import me.proxer.library.internal.adapter.UnitAdapter
import me.proxer.library.internal.interceptor.HeaderInterceptor
import me.proxer.library.internal.interceptor.HttpsEnforcingInterceptor
import me.proxer.library.internal.interceptor.LoginTokenInterceptor
import me.proxer.library.internal.interceptor.OneShotInterceptor
import me.proxer.library.internal.interceptor.RateLimitInterceptor
import me.proxer.library.util.ProxerUrls
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Entry point for access to the various APIs of Proxer.
 *
 * Before usage, you have to construct an instance through the [Builder].
 *
 * Note, that this is not a singleton. Each instance is separated from each other; You could even have instances with a
 * different api key.
 *
 * @author Ruben Gees
 */
class ProxerApi private constructor(retrofit: Retrofit) {

    companion object {
        /** Special api key to enable the test mode. */
        const val TEST_KEY = "test"

        private val CERTIFICATES = arrayOf(
            // https://censys.io/certificates/0687260331a72403d909f105e69bcf0d32e1bd2493ffc6d9206d11bcd6770739
            "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys=",
            // https://censys.io/certificates/16af57a9f676b0ab126095aa5ebadef22ab31119d644ac95cd4b93dbf3f26aeb
            "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=",
            // https://censys.io/certificates/1793927a0614549789adce2f8f34f7f0b66d0f3ae3a3b84d21ec15dbba4fadc7
            "sha256/58qRu/uxh4gFezqAcERupSkRYBlBAvfcw7mEjGPLnNU=",
            // https://censys.io/certificates/52f0e1c4e58ec629291b60317f074671b85d7ea80d5b07273463534b32b40234
            "sha256/grX4Ta9HpZx6tSHkmCrvpApTQGo67CYDnvprLg5yRME=",
            // https://censys.io/certificates/96bcec06264976f37460779acf28c5a7cfe8a3c0aae11a8ffcee05c0bddf08c6
            "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M="
        )

        private const val DEFAULT_USER_AGENT = "ProxerLibJava/" + BuildConfig.VERSION
    }

    /**
     * The respective API.
     */
    val notifications = NotificationsApi(retrofit)
        @JvmName("notifications") get

    /**
     * The respective API.
     */
    val user = UserApi(retrofit)
        @JvmName("user") get

    /**
     * The respective API.
     */
    val users = UsersApi(retrofit)
        @JvmName("users") get

    /**
     * The respective API.
     */
    val info = InfoApi(retrofit)
        @JvmName("info") get

    /**
     * The respective API.
     */
    val messenger = MessengerApi(retrofit)
        @JvmName("messenger") get

    /**
     * The respective API.
     */
    val chat = ChatApi(retrofit)
        @JvmName("chat") get

    /**
     * The respective API.
     */
    val list = ListApi(retrofit)
        @JvmName("list") get

    /**
     * The respective API.
     */
    val ucp = UcpApi(retrofit)
        @JvmName("ucp") get

    /**
     * The respective API.
     */
    val anime = AnimeApi(retrofit)
        @JvmName("anime") get

    /**
     * The respective API.
     */
    val manga = MangaApi(retrofit)
        @JvmName("manga") get

    /**
     * The respective API.
     */
    val forum = ForumApi(retrofit)
        @JvmName("forum") get

    /**
     * The respective API.
     */
    val media = MediaApi(retrofit)
        @JvmName("media") get

    /**
     * The respective API.
     */
    val apps = AppsApi(retrofit)
        @JvmName("apps") get

    /**
     * The respective API.
     */
    val comment = CommentApi(retrofit)
        @JvmName("comment") get

    /**
     * The respective API.
     */
    val wiki = WikiApi(retrofit)
        @JvmName("wiki") get

    /**
     *
     * You can set customized instances of the internally used libraries: Moshi, OkHttp and Retrofit.
     * Moreover you can specify your own [LoginTokenManager] and user agent.
     *
     * @constructor Constructs a new instance of the builder, with the passed api key.
     */
    class Builder(private val apiKey: String) {

        private var loginTokenManager: LoginTokenManager? = null
        private var userAgent: String? = null
        private var moshi: Moshi? = null
        private var client: OkHttpClient? = null
        private var retrofit: Retrofit? = null
        private var enableRateLimitProtection: Boolean = false

        /**
         * Sets a custom login token manager.
         */
        fun loginTokenManager(loginTokenManager: LoginTokenManager) = this.apply {
            this.loginTokenManager = loginTokenManager
        }

        /**
         * Sets a custom user agent.
         *
         * If not set, it will default to "ProxerLibJava/<version>"
         */
        fun userAgent(userAgent: String) = this.apply { this.userAgent = userAgent }

        /**
         * Sets a custom Moshi instance.
         *
         * Note that a internally a new instance will be constructed with the adjustments included you did on your
         * instance.
         */
        fun moshi(moshi: Moshi) = this.apply { this.moshi = moshi }

        /**
         * Sets a custom OkHttp instance.
         *
         * Note that internally a new instance will be constructed with the adjustments included you did on your
         * instance.
         */
        fun client(client: OkHttpClient) = this.apply { this.client = client }

        /**
         * Sets a custom Retrofit instance.
         *
         * Note that a internally a new instance will be constructed with the adjustments included you did on your
         * instance.
         */
        fun retrofit(retrofit: Retrofit) = this.apply { this.retrofit = retrofit }

        /**
         * Enables the rate limit protection to avoid users having to fill out captchas.
         */
        fun enableRateLimitProtection() = this.apply { this.enableRateLimitProtection = true }

        /**
         * Finally builds the [ProxerApi] with the provided adjustments.
         */
        fun build(): ProxerApi {
            val moshi = buildMoshi()

            return ProxerApi(buildRetrofit(moshi))
        }

        private fun buildRetrofit(moshi: Moshi): Retrofit {
            return (retrofit?.newBuilder() ?: Retrofit.Builder())
                .baseUrl(ProxerUrls.apiBase)
                .client(buildClient(moshi))
                .addCallAdapterFactory(ProxerResponseCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addConverterFactory(EnumRetrofitConverterFactory())
                .build()
        }

        private fun buildClient(moshi: Moshi): OkHttpClient {
            return (client?.newBuilder() ?: OkHttpClient.Builder())
                .apply {
                    interceptors().apply {
                        addAll(
                            0,
                            listOf(
                                HeaderInterceptor(apiKey, buildUserAgent()),
                                LoginTokenInterceptor(buildLoginTokenManager()),
                                HttpsEnforcingInterceptor(),
                                OneShotInterceptor()
                            )
                        )

                        if (enableRateLimitProtection) add(0, RateLimitInterceptor(moshi))
                    }

                    certificatePinner(constructCertificatePinner())
                }
                .build()
        }

        private fun buildLoginTokenManager(): LoginTokenManager {
            return loginTokenManager ?: DefaultLoginTokenManager()
        }

        private fun buildUserAgent(): String {
            return userAgent ?: DEFAULT_USER_AGENT
        }

        private fun constructCertificatePinner(): CertificatePinner {
            return CertificatePinner.Builder()
                .apply { CERTIFICATES.forEach { add(ProxerUrls.webBase.host, it) } }
                .build()
        }

        private fun buildMoshi(): Moshi {
            return (moshi?.newBuilder() ?: Moshi.Builder())
                .add(UnitAdapter())
                .add(DateAdapter())
                .add(PageAdapter())
                .add(HttpUrlAdapter())
                .add(ConferenceAdapter())
                .add(EpisodeInfoAdapter())
                .add(NotificationAdapter())
                .add(ConferenceInfoAdapter())
                .add(BooleanAdapterFactory())
                .add(NotificationInfoAdapter())
                .add(FixRatingDetailsAdapter())
                .add(UcpSettingConstraintAdapter())
                .add(DelimitedEnumSetAdapterFactory())
                .add(DelimitedStringSetAdapterFactory())
                .add(FallbackEnum.ADAPTER_FACTORY)
                .build()
        }
    }
}
