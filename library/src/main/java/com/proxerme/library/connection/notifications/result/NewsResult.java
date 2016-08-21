package com.proxerme.library.connection.notifications.result;

import com.proxerme.library.connection.ProxerResult;
import com.proxerme.library.connection.notifications.entitiy.News;
import com.squareup.moshi.Json;

/**
 * The Result of a {@link com.proxerme.library.connection.notifications.request.NewsRequest}.
 *
 * @author Ruben Gees
 */
public class NewsResult extends ProxerResult<News[]> {

    @Json(name = "data")
    private News[] data;

    private NewsResult() {

    }

    @Override
    public News[] getData() {
        return data;
    }
}
