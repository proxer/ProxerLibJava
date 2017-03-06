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
public final class ProxerCall<T> {

    private final Call<ProxerResponse<T>> internalCall;

    public ProxerCall(@NotNull final Call<ProxerResponse<T>> call) {
        this.internalCall = call;
    }

    public T execute() throws ProxerException {
        try {
            return processResponse(internalCall.execute());
        } catch (ProxerException error) {
            throw error;
        } catch (SocketTimeoutException error) {
            throw new ProxerException(ProxerException.ErrorType.TIMEOUT);
        } catch (IOException error) {
            throw new ProxerException(ProxerException.ErrorType.IO);
        } catch (JsonDataException error) {
            throw new ProxerException(ProxerException.ErrorType.PARSING);
        } catch (Throwable error) {
            throw new ProxerException(ProxerException.ErrorType.UNKNOWN);
        }
    }

    public void enqueue(@Nullable final ProxerCallback<T> callback, @Nullable final ProxerErrorCallback errorCallback) {
        internalCall.enqueue(new Callback<ProxerResponse<T>>() {
            @Override
            public void onResponse(Call<ProxerResponse<T>> call, Response<ProxerResponse<T>> response) {
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
            public void onFailure(final Call<ProxerResponse<T>> call, Throwable error) {
                if (errorCallback != null) {
                    if (error instanceof SocketTimeoutException) {
                        errorCallback.onError(new ProxerException(ProxerException.ErrorType.TIMEOUT));
                    } else if (error instanceof IOException) {
                        errorCallback.onError(new ProxerException(ProxerException.ErrorType.IO));
                    } else if (error instanceof JsonDataException) {
                        errorCallback.onError(new ProxerException(ProxerException.ErrorType.PARSING));
                    } else {
                        errorCallback.onError(new ProxerException(ProxerException.ErrorType.UNKNOWN));
                    }
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

    @SuppressWarnings("CloneDoesntCallSuperClone")
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
}
