package me.proxer.library.api;

import com.squareup.moshi.JsonDataException;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Class for performing a single request.
 * <p>
 * It provides nearly the same API as Retrofit's {@link Call}, thus allowing for synchronous and asynchronous
 * requests and cancellation.
 *
 * @param <T> The result type of the call.
 * @author Ruben Gees
 */
public final class ProxerCall<T> implements Cloneable {

    private final Call<ProxerResponse<T>> internalCall;

    ProxerCall(@NotNull final Call<ProxerResponse<T>> call) {
        this.internalCall = call;
    }

    /**
     * Executes the request synchronous.
     * <p>
     * Upon success, the result entity is returned. If an error occurs, the respective {@link ProxerException} is
     * thrown.
     */
    public T execute() throws ProxerException {
        try {
            return processResponse(internalCall.execute());
        } catch (final ProxerException error) {
            throw error;
        } catch (final Throwable error) {
            throw processNonProxerError(error);
        }
    }

    /**
     * Executes the request asynchronous.
     * <p>
     * Upon success, the result is returned on the {@code callback}. If an error occurs, the respective
     * {@link ProxerException} is returned on the {@code errorCallback}.
     * <p>
     * Note that this method does not know anything about the threads, it is running on. If you need to switch to
     * another thread (the main thread on Android for example), you have to do this yourself.
     */
    public void enqueue(@Nullable final ProxerCallback<T> callback, @Nullable final ProxerErrorCallback errorCallback) {
        internalCall.enqueue(new Callback<ProxerResponse<T>>() {
            @Override
            public void onResponse(final Call<ProxerResponse<T>> call, final Response<ProxerResponse<T>> response) {
                try {
                    if (callback != null) {
                        callback.onSuccess(processResponse(response));
                    }
                } catch (ProxerException error) {
                    if (errorCallback != null) {
                        errorCallback.onError(error);
                    }
                }
            }

            @Override
            public void onFailure(final Call<ProxerResponse<T>> call, final Throwable error) {
                if (errorCallback != null) {
                    errorCallback.onError(processNonProxerError(error));
                }
            }
        });
    }

    /**
     * Returns if this call has already been executed. It is an error to execute the same Call multiple times.
     */
    public boolean isExecuted() {
        return internalCall.isExecuted();
    }

    /**
     * Cancels this call.
     */
    public void cancel() {
        internalCall.cancel();
    }

    /**
     * Returns if the call has been canceled.
     */
    public boolean isCanceled() {
        return internalCall.isCanceled();
    }

    /**
     * Clones the call for reuse.
     */
    public ProxerCall<T> clone() {
        return new ProxerCall<>(internalCall.clone());
    }

    /**
     * Returns the underlying request.
     */
    public Request request() {
        return internalCall.request();
    }

    private T processResponse(@NotNull final Response<ProxerResponse<T>> response) throws ProxerException {
        if (response.isSuccessful()) {
            final ProxerResponse<T> proxerResponse = response.body();

            if (proxerResponse == null) {
                throw new ProxerException(ProxerException.ErrorType.PARSING);
            }

            if (proxerResponse.isSuccessful()) {
                return proxerResponse.getData();
            } else {
                throw new ProxerException(ProxerException.ErrorType.SERVER, proxerResponse.getCode(),
                        proxerResponse.getMessage());
            }
        } else {
            throw new ProxerException(ProxerException.ErrorType.IO);
        }
    }

    private ProxerException processNonProxerError(@NotNull final Throwable error) {
        if (error instanceof SocketTimeoutException) {
            return new ProxerException(ProxerException.ErrorType.TIMEOUT, error);
        } else if (error instanceof IOException) {
            if (error.getMessage().equals("Canceled")) {
                return new ProxerException(ProxerException.ErrorType.CANCELLED, error);
            } else {
                return new ProxerException(ProxerException.ErrorType.IO, error);
            }
        } else if (error instanceof JsonDataException) {
            return new ProxerException(ProxerException.ErrorType.PARSING, error);
        } else {
            return new ProxerException(ProxerException.ErrorType.UNKNOWN, error);
        }
    }
}
