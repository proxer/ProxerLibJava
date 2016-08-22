package com.proxerme.library.connection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringDef;

import com.proxerme.library.info.ProxerUrlHolder;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * TODO: Describe class
 *
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

    @NonNull
    @Size(min = 1)
    protected abstract Iterable<String> getEndpointPathSegments();

    @HttpMethod
    protected String getMethod() {
        return GET;
    }

    @Nullable
    protected RequestBody getRequestBody() {
        return null;
    }

    @NonNull
    protected Map<String, String> getQueryParameters() {
        return new HashMap<>(0);
    }

    private HttpUrl buildUrl() {
        HttpUrl.Builder builder = ProxerUrlHolder.getApiHost().newBuilder();

        for (String pathSegment : getEndpointPathSegments()) {
            builder.addPathSegment(pathSegment);
        }

        for (Map.Entry<String, String> queryParameter : getQueryParameters().entrySet()) {
            builder.addQueryParameter(queryParameter.getKey(), queryParameter.getValue());
        }

        return builder.build();
    }

    @StringDef({GET, HEAD, POST, DELETE, PUT, PATCH})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    protected @interface HttpMethod {
    }

}
