package com.proxerme.library.connection;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.WorkerThread;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.proxerme.library.util.SaveAllSharedPrefCookiePersistor;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Entry point for all interaction with the API. Before using, the user has to build an instance
 * with the {@link Builder}.
 * <br>
 * This class features two important methods:
 * <p>
 * 1) {@link #execute(ProxerRequest, ProxerCallback, ProxerErrorCallback)}
 * executes a passed {@link ProxerRequest} on a background thread. The results are returned to the
 * specified callbacks.
 * <p>
 * 2) {@link #executeSynchronized(ProxerRequest)}
 * executes a passed {@link ProxerRequest} on the same thread the method was invoked. This is useful
 * if the user wants to handle threading itself or is running code in a background thread already
 * (AsyncTask, IntentService, ...)
 * <p>
 * Both methods return their results on the main thread to make it possible to update views
 * directly.
 * <p>
 * The usage of this class might look like this:
 * <pre>
 * <code>
 * ProxerConnection connection = new ProxerConnection.Builder(context, "apiKey").build();
 *
 * connection.execute(new NewsRequest(0),{@literal new ProxerCallback<News[]>}() {
 *  {@literal @}Override
 *   public void onSuccess(News[] result) {
 *     //Do something with the result
 *   }
 * }, new ProxerErrorCallback() {
 *  {@literal @}Override
 *   public void onError(ProxerException exception) {
 *     //Handle the error
 *   }
 * });
 * </code>
 * </pre>
 *
 * @author Ruben Gees
 */
public final class ProxerConnection {

    private String apiKey;
    private Moshi moshi;
    private OkHttpClient httpClient;

    private boolean deliverCancelledRequests;

    private Handler handler = new Handler(Looper.getMainLooper());

    private ProxerConnection(@NonNull String apiKey, Moshi moshi, OkHttpClient httpClient,
                             boolean deliverCancelledRequests) {
        this.apiKey = apiKey;
        this.moshi = moshi;
        this.httpClient = httpClient;
        this.deliverCancelledRequests = deliverCancelledRequests;
    }

    /**
     * Executes the passed request on a background thread and returns the results on the callbacks
     * if passed. The callbacks are always called on the main thread.
     *
     * @param request       The request subclass.
     * @param callback      The callback for success.
     * @param errorCallback The callback in case of an error.
     * @param <T>           The type of the result. This is not a {@link ProxerResult} subclass.
     * @return A call object to allow cancellation of the request.
     */
    @RequiresPermission(android.Manifest.permission.INTERNET)
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
                if (call.isCanceled()) {
                    if (deliverCancelledRequests) {
                        deliverErrorResultOnMainThread(errorCallback,
                                new ProxerException(ProxerException.CANCELLED));
                    }
                } else {
                    deliverErrorResultOnMainThread(errorCallback,
                            new ProxerException(ProxerException.NETWORK));
                }
            }
        });

        return new ProxerCall(call);
    }

    /**
     * Executes the passed request and returns the results immediately.
     *
     * @param request The request subclass.
     * @param <T>     The type of the result. This is not a {@link ProxerResult} subclass.
     * @return The results.
     * @throws ProxerException The exception in case of an error.
     */
    @WorkerThread
    @RequiresPermission(android.Manifest.permission.INTERNET)
    public <T> T executeSynchronized(@NonNull final ProxerRequest<T> request)
            throws ProxerException {
        final Call call = httpClient.newCall(request.build());

        try {
            return processResponse(request, call.execute());
        } catch (IOException exception) {
            if (call.isCanceled()) {
                throw new ProxerException(ProxerException.CANCELLED);
            } else {
                throw new ProxerException(ProxerException.NETWORK);
            }
        }
    }

    /**
     * Returns the API key the user passed.
     *
     * @return The API key.
     */
    @NonNull
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Returns the Moshi instance for usage at other parts in the application.
     *
     * @return The Moshi instance.
     */
    @NonNull
    public Moshi getMoshi() {
        return moshi;
    }

    /**
     * Returns the OkHttpClient instance for usage at other parts in the application.
     *
     * @return THe OkHttpClient instance.
     */
    @NonNull
    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    private <T> T processResponse(ProxerRequest<T> request, Response response)
            throws ProxerException {
        try {
            if (!response.isSuccessful()) {
                throw new ProxerException(ProxerException.NETWORK);
            }

            ProxerResult<T> result = request.parse(moshi, response.body());

            if (result.isSuccess()) {
                return result.getData();
            } else {
                throw new ProxerException(ProxerException.PROXER, result.getMessage(),
                        result.getCode());
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

    /**
     * Builder for the {@link ProxerConnection} class. This allows for customization.
     */
    public static class Builder {

        private static final String API_KEY_HEADER = "proxer-api-key";

        private String apiKey;
        private Context context;
        private boolean deliverCancelledRequests;

        private Moshi moshi;
        private CookieJar cookieJar;
        private OkHttpClient httpClient;

        /**
         * The constructor.
         *
         * @param context The context. This should be the application context.
         * @param apiKey  The API key to use.
         */
        public Builder(@NonNull String apiKey, @NonNull Context context) {
            this.context = context;
            this.apiKey = apiKey;
        }

        /**
         * The constructor. This differs from the other constructor by the ability to pass a custom
         * CookieJar instead of the one provided by the library.
         *
         * @param cookieJar The CookieJar to use.
         * @param apiKey    The API key to use.
         */
        public Builder(@NonNull String apiKey, @NonNull CookieJar cookieJar) {
            this.cookieJar = cookieJar;
            this.apiKey = apiKey;
        }

        /**
         * Builds the connection with all specified options.
         * If for one option nothing was passed, the default configuration will be used.
         *
         * @return The new connection.
         */
        public ProxerConnection build() {
            configureMoshi();
            configureCookieJar();
            configureOkHttp();

            return new ProxerConnection(apiKey, moshi, httpClient, deliverCancelledRequests);
        }

        /**
         * Allows to set a custom Moshi instance.
         *
         * @param moshi The custom instance.
         * @return This builder.
         */
        @NonNull
        public Builder withCustomMoshi(Moshi moshi) {
            this.moshi = moshi;

            return this;
        }

        /**
         * Allows to set a custom OkHttpClient.
         * <br>
         * Note that in all cases an Interceptor for the API
         * key and an CookieJar (You can specify your own with the
         * {@link #Builder(String, CookieJar)} constructor) will be added.
         *
         * @param httpClient The custom OkHttpClient.
         * @return This builder.
         */
        @NonNull
        public Builder withCustomOkHttp(OkHttpClient httpClient) {
            this.httpClient = httpClient;

            return this;
        }

        /**
         * Allows to set if requests should be delivered on the errorCallback if they have been
         * cancelled.
         *
         * @param enable True, if the result should be delivered.
         * @return This builder.
         */
        public Builder withDeliverCancelledRequests(boolean enable) {
            this.deliverCancelledRequests = enable;

            return this;
        }

        private void configureMoshi() {
            if (moshi == null) {
                moshi = new Moshi.Builder()
                        .build();
            }
        }

        private void configureCookieJar() {
            if (cookieJar == null) {
                cookieJar = new PersistentCookieJar(new SetCookieCache(),
                        new SaveAllSharedPrefCookiePersistor(context));
            }
        }

        private void configureOkHttp() {
            OkHttpClient.Builder builder;

            if (httpClient == null) {
                builder = new OkHttpClient.Builder();
            } else {
                builder = httpClient.newBuilder();
            }

            httpClient = builder.cookieJar(cookieJar)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            return chain.proceed(chain.request().newBuilder()
                                    .addHeader(API_KEY_HEADER, apiKey)
                                    .build());
                        }
                    })
                    .build();
        }
    }
}
