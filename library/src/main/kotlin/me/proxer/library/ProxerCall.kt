package me.proxer.library

import com.squareup.moshi.JsonDataException
import me.proxer.library.internal.ProxerResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Class for performing a single request.
 *
 * It provides nearly the same API as Retrofit's [Call], thus allowing for synchronous and asynchronous
 * requests and cancellation.
 *
 * @param <T> The result type of the call.
 * @author Ruben Gees
 */
class ProxerCall<T> internal constructor(private val internalCall: Call<ProxerResponse<T>>) : Cloneable {

    /**
     * Returns if this call has already been executed. It is an error to execute the same Call multiple times.
     */
    val isExecuted: Boolean
        get() = internalCall.isExecuted

    /**
     * Returns if the call has been canceled.
     */
    val isCanceled: Boolean
        get() = internalCall.isCanceled

    /**
     * Executes the request synchronous.
     *
     * Upon success, the result entity is returned. If an error occurs, the respective [ProxerException] is
     * thrown.
     */
    @Suppress("TooGenericExceptionCaught", "RethrowCaughtException")
    @Throws(ProxerException::class)
    fun execute(): T? {
        return try {
            processResponse(internalCall.execute())
        } catch (error: ProxerException) {
            throw error
        } catch (error: Exception) {
            throw processNonProxerError(error)
        }
    }

    /**
     * Executes the request synchronous.
     *
     * Upon success, the result entity is returned. If an error occurs or the response is `null`,
     * the respective [ProxerException] is thrown.
     */
    @Throws(ProxerException::class)
    fun safeExecute(): T {
        return execute() ?: throw processNonProxerError(NullPointerException("Response is null."))
    }

    /**
     * Executes the request asynchronous.
     *
     * Upon success, the result is returned on the [callback]. If an error occurs, the respective
     * [ProxerException] is returned on the [errorCallback].
     *
     * Note that this method does not know anything about the threads, it is running on. If you need to switch to
     * another thread (the main thread on Android for example), you have to do this yourself.
     */
    @JvmOverloads
    fun enqueue(callback: ((T?) -> Unit)? = null, errorCallback: ((ProxerException) -> Unit)? = null) {
        internalCall.enqueue(object : Callback<ProxerResponse<T>> {
            override fun onResponse(call: Call<ProxerResponse<T>>, response: Response<ProxerResponse<T>>) {
                try {
                    callback?.invoke(processResponse(response))
                } catch (error: ProxerException) {
                    errorCallback?.invoke(error)
                }
            }

            override fun onFailure(call: Call<ProxerResponse<T>>, error: Throwable) {
                if (error is Exception && errorCallback != null) {
                    errorCallback.invoke(processNonProxerError(error))
                }
            }
        })
    }

    /**
     * Cancels this call.
     */
    fun cancel() {
        internalCall.cancel()
    }

    /**
     * Clones the call for reuse.
     */
    public override fun clone(): ProxerCall<T> {
        return ProxerCall(internalCall.clone())
    }

    /**
     * Returns the underlying request.
     */
    fun request(): Request {
        return internalCall.request()
    }

    private fun processResponse(response: Response<ProxerResponse<T>>): T? {
        if (response.isSuccessful) {
            val proxerResponse = response.body() ?: throw ProxerException(ProxerException.ErrorType.PARSING)

            if (proxerResponse.isSuccessful) {
                return proxerResponse.data
            } else {
                val message = when {
                    proxerResponse.data != null -> "${proxerResponse.message}\n${proxerResponse.data}"
                    else -> proxerResponse.message
                }

                throw ProxerException(
                    ProxerException.ErrorType.SERVER,
                    proxerResponse.code,
                    message
                )
            }
        } else {
            response.errorBody()?.also { it.close() }

            if (response.code() in 500..599) {
                throw ProxerException(
                    ProxerException.ErrorType.SERVER,
                    message = "Unsuccessful request: ${response.code()}",
                    serverErrorType = ProxerException.ServerErrorType.UNKNOWN
                )
            } else {
                throw ProxerException(
                    ProxerException.ErrorType.IO,
                    message = "Unsuccessful request: ${response.code()}"
                )
            }
        }
    }

    private fun processNonProxerError(error: Exception): ProxerException {
        return when (error) {
            is SocketTimeoutException -> ProxerException(
                ProxerException.ErrorType.TIMEOUT,
                cause = error
            )
            is IOException -> when {
                error.message == "Canceled" -> ProxerException(
                    ProxerException.ErrorType.CANCELLED,
                    cause = error
                )
                else -> ProxerException(ProxerException.ErrorType.IO, cause = error)
            }
            is JsonDataException -> ProxerException(
                ProxerException.ErrorType.PARSING,
                cause = error
            )
            else -> ProxerException(
                ProxerException.ErrorType.UNKNOWN,
                cause = error
            )
        }
    }
}
