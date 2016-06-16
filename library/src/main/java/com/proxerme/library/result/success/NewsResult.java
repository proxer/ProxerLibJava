package com.proxerme.library.result.success;

import android.support.annotation.NonNull;

import com.proxerme.library.entity.News;
import com.proxerme.library.result.ProxerResult;

/**
 * TODO: Describe Class
 *
 * @author Ruben Gees
 */
public class NewsResult implements ProxerResult<News[]> {

    private News[] item;

    public NewsResult(@NonNull News[] item) {
        this.item = item;
    }

    @Override
    @NonNull
    public News[] getItem() {
        return item;
    }
}
