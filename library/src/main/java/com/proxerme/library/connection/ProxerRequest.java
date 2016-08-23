package com.proxerme.library.connection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Pair;

import com.proxerme.library.info.ProxerUrlHolder;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Base class for all requests. A subclass has to implement the {@link #parse(Moshi, ResponseBody)},
 * {@link #getApiClass()} and {@link #getApiEndpoint()} methods. Moreover it can implement the
 * {@link #getMethod()} method to specify the {@link ProxerRequest.HttpMethod} the request is going
 * to use.
 * To specify arguments, the methods {@link #getQueryParameters()} and {@link #getRequestBody()} can
 * be overridden.
 *
 * @param <T> The type of the result. This is not the {@link ProxerResult} subclass, but the entity
 *            type this request will return.
 * @author Ruben Gees
 */
public abstract class ProxerRequest<T> {

    protected static final String GET = "GET";
    protected static final String HEAD = "HEAD";
    protected static final String POST = "POST";
    protected static final String DELETE = "DELETE";
    protected static final String PUT = "PUT";
    protected static final String PATCH = "PATCH";

    Request build() {
        return new Request.Builder()
                .url(buildUrl())
                .method(getMethod(), getRequestBody())
                .build();
    }

    protected abstract ProxerResult<T> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException;

    /**
     * Returns the class the request residents in. For a
     * {@link com.proxerme.library.connection.notifications.request.NewsRequest} this would be
     * "notifications".
     *
     * @return The API class.
     */
    @NonNull
    protected abstract String getApiClass();

    /**
     * Returns the endpoint of the request. For a
     * {@link com.proxerme.library.connection.notifications.request.NewsRequest} this would be
     * "news".
     *
     * @return The API endpoint.
     */
    @NonNull
    protected abstract String getApiEndpoint();

    /**
     * Returns the http method. Allows a subclass to specify another method than GET.
     *
     * @return The http method.
     */
    @HttpMethod
    protected String getMethod() {
        return GET;
    }

    /**
     * Returns the request body for this request. This allows a subclass to add POST parameters for
     * example.
     *
     * @return The request body.
     */
    @Nullable
    protected RequestBody getRequestBody() {
        return null;
    }

    /**
     * Returns the query parameters. Each map entry must have the name of the parameter as key and
     * the value of the parameter as value.
     *
     * @return The map of parameters.
     */
    @NonNull
    protected Iterable<Pair<String, ?>> getQueryParameters() {
        return new ArrayList<>(0);
    }

    private HttpUrl buildUrl() {
        HttpUrl.Builder builder = ProxerUrlHolder.getApiHost().newBuilder();

        builder.addPathSegment(getApiClass());
        builder.addPathSegment(getApiEndpoint());

        for (Pair<String, ?> queryParameter : getQueryParameters()) {
            if (queryParameter.second != null) {
                builder.addQueryParameter(queryParameter.first,
                        String.valueOf(queryParameter.second));
            }
        }

        return builder.build();
    }

    /**
     * Annotation representing the valid http methods.
     */
    @StringDef({GET, HEAD, POST, DELETE, PUT, PATCH})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    protected @interface HttpMethod {
    }

}
