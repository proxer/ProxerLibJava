package com.proxerme.library.connection.notifications.result;

import android.support.annotation.NonNull;

import com.afollestad.bridge.annotations.Body;
import com.proxerme.library.connection.notifications.entitiy.News;
import com.proxerme.library.interfaces.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsResult implements ProxerResult<News[]> {

    @Body(name = "data")
    News[] item;

    NewsResult() {

    }

    public NewsResult(@NonNull News[] item) {
        this.item = item;
    }

    @Override
    @NonNull
    public News[] getItem() {
        return item;
    }
}
