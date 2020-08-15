package me.proxer.library.internal.interceptor

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import me.proxer.library.ProxerException.ServerErrorType.RATE_LIMIT
import me.proxer.library.internal.ProxerResponse
import me.proxer.library.util.ProxerUrls
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.util.Date
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

internal class RateLimitInterceptor(private val moshi: Moshi) : Interceptor {

    private companion object {
        private const val WAIT_THRESHOLD = 2_000

        private val errorMediaType = "application/json".toMediaTypeOrNull()
        private val responseType = Types.newParameterizedType(ProxerResponse::class.java, Unit::class.java)

        private val limits = mapOf(
            "anime" to Limit(20, 360_000),
            "apps" to Limit(100, 30_000), // Not enforced by api.
            "chat" to Limit(30, 30_000),
            "comment" to Limit(100, 30_000), // Not enforced by api.
            "files" to Limit(30, 100_000),
            "forum" to Limit(100, 30_000), // Not enforced by api.
            "info" to Limit(60, 360_000),
            "list" to Limit(60, 60_000),
            "manga" to Limit(10, 300_000),
            "media" to Limit(100, 30_000), // Not enforced by api.
            "messenger" to Limit(100, 50_000),
            "misc" to Limit(100, 30_000), // Not enforced by api.
            "notifications" to Limit(100, 30_000), // Not enforced by api.
            "ucp" to Limit(40, 60_000),
            "user" to Limit(40, 360_000),
            "users" to Limit(40, 360_000),
            "wiki" to Limit(100, 30_000) // Not enforced by api.
        )
    }

    private var state = limits.mapValues { State(0, null) }

    private val lock = ReentrantReadWriteLock()

    @Suppress("ReturnCount")
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        if (oldRequest.url.host != ProxerUrls.apiBase.host) {
            return chain.proceed(oldRequest)
        }

        val apiClass = oldRequest.url.pathSegments.getOrNull(2)
        val limit = limits[apiClass]

        if (apiClass == null || limit == null) {
            return chain.proceed(oldRequest)
        }

        return intercept(apiClass, limit, chain, oldRequest)
    }

    @Suppress("ReturnCount")
    private fun intercept(
        apiClass: String,
        limit: Limit,
        chain: Interceptor.Chain,
        oldRequest: Request
    ): Response {
        val (requests, firstRequest) = lock.read { state.getValue(apiClass) }
        val timeDifferenceMillis = if (firstRequest != null) Date().time - firstRequest.time else null

        if (timeDifferenceMillis == null || timeDifferenceMillis > limit.millis) {
            lock.write { state = state.update(apiClass, 1, Date()) }

            return chain.proceed(oldRequest)
        } else if (requests + 2 >= limit.maxRequests) {
            return if ((limit.millis - timeDifferenceMillis) < WAIT_THRESHOLD) {
                Thread.sleep(timeDifferenceMillis)

                intercept(chain)
            } else {
                val errorBody = moshi
                    .adapter<ProxerResponse<Unit?>>(responseType)
                    .toJson(ProxerResponse(true, "Rate limit", RATE_LIMIT.code, null))

                Response.Builder()
                    .body(errorBody.toResponseBody(errorMediaType))
                    .protocol(Protocol.HTTP_1_1)
                    .request(oldRequest)
                    .code(200)
                    .message("")
                    .build()
            }
        } else {
            lock.write { state = state.update(apiClass, requests + 1, firstRequest) }

            return chain.proceed(oldRequest)
        }
    }

    private fun Map<String, State>.update(apiClass: String, requests: Int, firstRequest: Date?): Map<String, State> {
        return toMutableMap().apply { put(apiClass, State(requests, firstRequest)) }
    }

    private data class Limit(val maxRequests: Int, val millis: Int)
    private data class State(val requests: Int, val firstRequest: Date?)
}
