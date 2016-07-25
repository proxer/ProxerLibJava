package com.proxerme.library.connection.notifications.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * The Result of a {@link com.proxerme.library.connection.notifications.request.NewsRequest}.
 *
 * @author Ruben Gees
 */
public class NewsResult implements ProxerResult<News[]> {

    @Body(name = "data")
    News[] item;

    NewsResult() {

    }

    /**
     * @param item The array of News.
     */
    public NewsResult(@NonNull News[] item) {
        this.item = item;
    }

    /**
     * Returns the received News.
     *
     * @return The array of News.
     */
    @Override
    @NonNull
    public News[] getItem() {
        return item;
    }
}
