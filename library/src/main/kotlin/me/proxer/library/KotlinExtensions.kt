package me.proxer.library

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T : Any> ProxerCall<T>.await(): T {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }

        enqueue(
            { result ->
                if (result != null) {
                    continuation.resume(result)
                } else {
                    continuation.resumeWithException(NullPointerException())
                }
            },
            { error -> continuation.resumeWithException(error) }
        )
    }
}

@JvmName("awaitNullable")
suspend fun <T : Any> ProxerCall<T?>.await(): T? {
    return suspendCancellableCoroutine { continuation ->
        continuation.invokeOnCancellation {
            cancel()
        }

        enqueue(
            { result -> continuation.resume(result) },
            { error -> continuation.resumeWithException(error) }
        )
    }
}
