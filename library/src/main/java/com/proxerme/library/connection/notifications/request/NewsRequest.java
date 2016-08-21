package com.proxerme.library.connection.notifications.request;

import android.support.annotation.NonNull;

import com.proxerme.library.connection.ProxerRequest;
import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.connection.notifications.result.NewsResult;
import com.proxerme.library.parameters.TypeParameter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * The Request for obtaining the latest News. This API uses pagination.
 *
 * @author Ruben Gees
 */
public class NewsRequest extends ProxerRequest<News[]> {

    private int page;
    private String type;

    public NewsRequest(int page) {
        this.page = page;
    }

    public NewsRequest withType(@TypeParameter.Type String type) {
        this.type = type;

        return this;
    }

    @Override
    public ProxerResult<News[]> parse(@NonNull Moshi moshi, @NonNull ResponseBody body)
            throws IOException {
        return moshi.adapter(NewsResult.class).fromJson(body.source());
    }

    @NonNull
    @Override
    protected Iterable<String> getEndpointPathSegments() {
        return Arrays.asList("notifications", "news");
    }

    @NonNull
    @Override
    protected Map<String, String> getQueryParameters() {
        HashMap<String, String> result = new HashMap<>();

        result.put("p", String.valueOf(page));

        if (type != null) {
            result.put("type", type);
        }

        return result;
    }
}
