package com.proxerme.library.api;

import com.proxerme.library.entitiy.ProxerResponse;
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
 * TODO: Describe class
 *
 * @author Ruben Gees
 */
public final class ProxerCall<T> implements Cloneable {

    private final Call<ProxerResponse<T>> internalCall;

    public ProxerCall(@NotNull final Call<ProxerResponse<T>> call) {
        this.internalCall = call;
    }

    public T execute() throws ProxerException {
        try {
            return processResponse(internalCall.execute());
        } catch (final ProxerException error) {
            throw error;
        } catch (final Throwable error) {
            throw processNonProxerError(error);
        }
    }

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

    public boolean isExecuted() {
        return internalCall.isExecuted();
    }

    public void cancel() {
        internalCall.cancel();
    }

    public boolean isCanceled() {
        return internalCall.isCanceled();
    }

    public ProxerCall<T> clone() {
        return new ProxerCall<>(internalCall.clone());
    }

    public Request request() {
        return internalCall.request();
    }

    private T processResponse(@NotNull final Response<ProxerResponse<T>> response) throws ProxerException {
        if (response.isSuccessful()) {
            final ProxerResponse<T> proxerResponse = response.body();

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
            return new ProxerException(ProxerException.ErrorType.TIMEOUT);
        } else if (error instanceof IOException) {
            return new ProxerException(ProxerException.ErrorType.IO);
        } else if (error instanceof JsonDataException) {
            return new ProxerException(ProxerException.ErrorType.PARSING);
        } else {
            return new ProxerException(ProxerException.ErrorType.UNKNOWN);
        }
    }
}
