package com.proxerme.library.connection;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ProxerConnection {

    private String apiKey;
    private Moshi moshi;
    private OkHttpClient httpClient;

    private Handler handler = new Handler(Looper.getMainLooper());

    private ProxerConnection(@NonNull String apiKey, Moshi moshi, OkHttpClient httpClient) {
        this.apiKey = apiKey;
        this.moshi = moshi;
        this.httpClient = httpClient;
    }

    public <T> ProxerCall execute(@NonNull final ProxerRequest<T> request,
                                  @Nullable final ProxerCallback<T> callback,
                                  @Nullable final ProxerErrorCallback errorCallback) {
        Call call = httpClient.newCall(request.build());

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    deliverResultOnMainThread(callback, processResponse(request, response));
                } catch (ProxerException exception) {
                    deliverErrorResultOnMainThread(errorCallback, exception);
                }
            }

            @Override
            public void onFailure(Call call, IOException exception) {
                deliverErrorResultOnMainThread(errorCallback,
                        new ProxerException(ProxerException.NETWORK));
            }
        });

        return new ProxerCall(call);
    }

    public <T> T executeSynchronized(@NonNull final ProxerRequest<T> request)
            throws ProxerException {
        try {
            return processResponse(request, httpClient.newCall(request.build()).execute());
        } catch (IOException exception) {
            throw new ProxerException(ProxerException.NETWORK);
        }
    }

    public String getApiKey() {
        return apiKey;
    }

    public Moshi getMoshi() {
        return moshi;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    private <T> T processResponse(ProxerRequest<T> request, Response response)
            throws ProxerException {
        try {
            if (response.isSuccessful()) {
                ProxerResult<T> result = request.parse(moshi, response.body());

                if (result.isSuccess()) {
                    return result.getData();
                } else {
                    throw new ProxerException(ProxerException.PROXER, result.getMessage(),
                            result.getCode());
                }
            } else {
                throw new ProxerException(ProxerException.NETWORK);
            }
        } catch (JsonDataException | IOException exception) {
            throw new ProxerException(ProxerException.UNPARSABLE);
        }
    }

    private <T> void deliverResultOnMainThread(@Nullable final ProxerCallback<T> callback,
                                               final T result) {
        if (callback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(result);
                }
            });
        }
    }

    private void deliverErrorResultOnMainThread(@Nullable final ProxerErrorCallback callback,
                                                final ProxerException exception) {
        if (callback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.onError(exception);
                }
            });
        }
    }

    public static class Builder {

        private Context context;
        private String apiKey;
        private Moshi moshi;
        private OkHttpClient httpClient;

        public Builder(@NonNull Context context, @NonNull String apiKey) {
            this.context = context;
            this.apiKey = apiKey;
        }

        public ProxerConnection build() {
            configureMoshi();
            configureOkHttp();

            return new ProxerConnection(apiKey, moshi, httpClient);
        }

        public Builder moshi(Moshi moshi) {
            this.moshi = moshi;

            return this;
        }

        public Builder okHttp(OkHttpClient httpClient) {
            this.httpClient = httpClient;

            return this;
        }

        private void configureMoshi() {
            if (moshi == null) {
                moshi = new Moshi.Builder()
                        .build();
            }
        }

        private void configureOkHttp() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (httpClient == null) {
                httpClient = new OkHttpClient.Builder()
                        .build();
            } else {
                builder = httpClient.newBuilder();
            }

            httpClient = builder.cookieJar(new PersistentCookieJar(new SetCookieCache(),
                    new SharedPrefsCookiePersistor(context)))
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            return chain.proceed(chain.request().newBuilder()
                                    .addHeader("proxer-api-key", apiKey)
                                    .build());
                        }
                    })
                    .build();
        }
    }
}